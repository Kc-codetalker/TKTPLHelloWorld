package id.ac.ui.cs.mobileprogramming.kace.helloworld;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    LiveData<List<String>> wifiNames = new MutableLiveData<>();

    public LiveData<List<String>> getWifiNames() {
        return wifiNames;
    }

    public void setWifiNames(List<String> wifiNames) {
        ((MutableLiveData<List<String>>) this.wifiNames).setValue(wifiNames);
    }
}
