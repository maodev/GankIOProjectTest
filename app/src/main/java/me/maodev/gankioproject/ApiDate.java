package me.maodev.gankioproject;

import java.util.ArrayList;


/**
 * Created by maoyu on 16/12/6.
 */

public class ApiDate {
    private boolean error;
    private ArrayList<ResultModel> results;

    public ApiDate() {
    }

    public ApiDate(boolean error, ArrayList<ResultModel> results) {
        this.error = error;
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<ResultModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultModel> results) {
        this.results = results;
    }
}
