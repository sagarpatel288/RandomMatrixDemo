package com.example.android.evince;

import android.os.Bundle;
import android.view.View;

import com.example.android.evince.databinding.ActivityMainBinding;
import com.example.android.evince.pojo.Matrix;
import com.example.android.evince.viewutils.ViewUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;
    private int mRows;
    private int mColumns;
    private Random random = new Random();
    private List<Matrix> matrixList = new ArrayList<>();

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
        setRecyclerView(getRows(), getColumns());
    }

    private void setRecyclerView(int rows, int columns) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columns);
        
    }

    private int getRandomNumber(int row, int column) {
        return random.nextInt(getTotalItems(row, column) + 1);
    }

    private int getTotalItems(int rows, int columns) {
        return rows * columns;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_mbtn_apply:
                if (isValidInput()) {

                }
                break;
        }
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
