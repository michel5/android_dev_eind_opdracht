package com.example.michel_desktop.mobiledev.ui;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.michel_desktop.mobiledev.BinanceApiService;
import com.example.michel_desktop.mobiledev.GridCell;
import com.example.michel_desktop.mobiledev.R;
import com.example.michel_desktop.mobiledev.BalanceAdapter;
import com.example.michel_desktop.mobiledev.models.BalanceModel;
import com.example.michel_desktop.mobiledev.vieuwModel.MainVieuwModel;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //Local variables
    private BalanceAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<BalanceModel> balanceModelList = new ArrayList<>();
    public static MainVieuwModel mVieuwModel;

    //app database model
    //public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestBalanceData();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = findViewById(R.id.balance_recycler_vieuw);

		mVieuwModel = new MainVieuwModel(getApplicationContext());
		mVieuwModel.getBalance().observe(this, new Observer<List<BalanceModel>>() {
			@Override
			public void onChanged(@Nullable List<BalanceModel> reminders) {
				balanceModelList = reminders;
				updateUI();
			}
		});

		ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
			@Override
			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
				return false;
			}

			@Override
			public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
				System.out.println(viewHolder.getAdapterPosition());

				//haal de gridcell op
				GridCell gridCell = mVieuwModel.getmGridCell().get(viewHolder.getAdapterPosition());

				//haal de balance model op uit de database
				BalanceModel balanceModel = mVieuwModel.getAllBalance(gridCell.getCointag()).get(0);

				//start intent
				Intent intent = new Intent(MainActivity.this, Balance_details_activity.class);
				intent.putExtra("cointag",balanceModel.getAsset());
				intent.putExtra("balance", ""+Double.valueOf(balanceModel.getFree())
						+ Double.valueOf(balanceModel.getLocked()));
				intent.putExtra("available", balanceModel.getFree());

				//start activity
				startActivity(intent);

				//update ui
				updateUI();
			}
		};

		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
		itemTouchHelper.attachToRecyclerView(mRecyclerView);

        //maak de recycler vieuw
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1,
                LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BalanceAdapter(this, ((MainVieuwModel) mVieuwModel).getmGridCell());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, KeysSettingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void updateUI() {
        //comperator de list
        ((MainVieuwModel) mVieuwModel).comperateList();
		List<GridCell> gridCellList =  ((MainVieuwModel) mVieuwModel).getmGridCell();

        if (mAdapter == null) {
            mAdapter = new BalanceAdapter(this, gridCellList);
            mRecyclerView.setAdapter(mAdapter);
        } else {
			mAdapter.swapList(gridCellList);
        }
    }


    private void requestBalanceData() {
        BinanceApiService service = BinanceApiService.retrofit.create(BinanceApiService.class);
        /**
         * Make an a-synchronous call by enqueing and definition of callbacks.
         */
        Call <List<BalanceModel>> call= service.getBalance();
        call.enqueue(new Callback<List<BalanceModel>>() {

            @Override
            public void onResponse(Call<List<BalanceModel>> call, Response<List<BalanceModel>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    ((MainVieuwModel) mVieuwModel).addGridCell(new GridCell(response.body().get(i).getAsset(),
                            Double.valueOf(response.body().get(i).getLocked())));

                    //kijk of balance model bestaat. Als er null terug komt moet de model toegevoegd worden aan de database
					List<BalanceModel> listBalanceModel = ((MainVieuwModel) mVieuwModel).
							getAllBalance(response.body().get(i).getAsset());

					//als list 0 is moet het model toegevoegd worden
					if(listBalanceModel.size() == 0){
						mVieuwModel.insert(response.body().get(i));
					} else {

						BalanceModel balanceModelUitDB = listBalanceModel.get(0);

						//haal het balance model uit de response
						BalanceModel balanceModelUitApi = response.body().get(i);

						//kijk of model geupdate moet worden
						if (balanceModelUitApi.getLocked() != balanceModelUitDB.getLocked() ||
								balanceModelUitApi.getFree() != balanceModelUitDB.getFree()) {
							balanceModelUitDB.setFree(balanceModelUitApi.getFree());
							balanceModelUitDB.setLocked(balanceModelUitApi.getLocked());

							//update model
							((MainVieuwModel) mVieuwModel).update(balanceModelUitDB);
						}
					}
                }

                //update recyclervieuw
                updateUI();
            }

            @Override
            public void onFailure(Call <List<BalanceModel>> call, Throwable t) {
                Log.d("error", t.toString());
                updateUI();
            }
        });
    }
}
