package id.ac.ui.cs.mobileprogramming.kace.helloworld;

import android.net.wifi.ScanResult;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    LiveData<List<ScanResult>> wifis = new MutableLiveData<>();

    public LiveData<List<ScanResult>> getWifis() {
        return wifis;
    }

    public void setWifis(List<ScanResult> wifis) {
        ((MutableLiveData<List<ScanResult>>) this.wifis).setValue(wifis);
    }
}
