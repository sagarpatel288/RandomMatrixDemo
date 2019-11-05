package com.example.android.evince;

import android.os.Bundle;
import android.view.View;

import com.example.android.evince.adapter.RvMatrixAdapter;
import com.example.android.evince.database.AppDatabase;
import com.example.android.evince.databinding.ActivityMainBinding;
import com.example.android.evince.pojo.Matrix;
import com.example.android.evince.utils.Utils;
import com.example.android.evince.viewutils.ViewUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;
    private int mRows;
    private int mColumns;
    private Random random = new Random();
    private List<Matrix> mList = new ArrayList<>();

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handleViews();
        ViewUtils.setOnClickListener(this, mBinding.viewMbtnApply, mBinding.viewMbtnRandom);
    }

    private void handleViews() {
        if (Utils.isNotNullNotEmpty(AppDatabase.getInstance(this).getAppDao().getAllMatrices())){
            mList = AppDatabase.getInstance(this).getAppDao().getAllMatrices();
            setRows(mList.get(0).getRows());
            setColumns(mList.get(0).getColumns());
        }
        setRecyclerView(getRows(), getColumns());
    }

    private void setRecyclerView(int rows, int columns) {
        mList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columns != 0 ? columns : 5, columns > rows ? RecyclerView.HORIZONTAL : RecyclerView.VERTICAL, false);
        mBinding.viewRv.setLayoutManager(gridLayoutManager);
        mList = getMatrix(rows, columns);
        RvMatrixAdapter rvMatrixAdapter = new RvMatrixAdapter(this, mList);
        mBinding.viewRv.setAdapter(rvMatrixAdapter);
    }

    public int getRows() {
        return mRows;
    }

    public void setRows(int mRows) {
        this.mRows = mRows;
    }

    public int getColumns() {
        return mColumns;
    }

    public void setColumns(int mColumns) {
        this.mColumns = mColumns;
    }

    private List<Matrix> getMatrix(int rows, int columns) {
        int max = getTotalItems(rows, columns);
        for (int i = 0; i < max; i++) {
            mList.add(new Matrix(i));
        }
        return mList;
    }

    private int getTotalItems(int rows, int columns) {
        return rows * columns;
    }

    private int getRandomNumber(int row, int column) {
        return random.nextInt(getTotalItems(row, column) + 1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_mbtn_apply:
                if (isValidInput()) {
                    setRecyclerView();
                }
                break;
        }
    }

    private void setRecyclerView() {
        setRows(Integer.parseInt(StringUtils.getString(mBinding.viewTietRows, "0")));
        setColumns(Integer.parseInt(StringUtils.getString(mBinding.viewTietColumns, "0")));
        setRecyclerView(getRows(), getColumns());
    }

    private boolean isValidInput() {
        if (!ViewUtils.hasTextValue(mBinding.viewTietRows)) {
            showMessage(getString(R.string.st_error_row_can_not_be_empty));
            return false;
        } else if (!ViewUtils.hasTextValue(mBinding.viewTietColumns)) {
            showMessage(getString(R.string.st_error_column_can_not_be_empty));
            return false;
        }
        return true;
    }

    private void showMessage(String message) {
        Snackbar.make(mBinding.viewCoor, message, Snackbar.LENGTH_LONG);
    }
}
