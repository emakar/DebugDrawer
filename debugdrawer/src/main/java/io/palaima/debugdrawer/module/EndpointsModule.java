package io.palaima.debugdrawer.module;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import io.palaima.debugdrawer.R;

public class EndpointsModule implements DrawerModule {

    public interface OnEndpointChanged {

        void onEndpointChange(String urlText);
    }
    
    private final List<String>      endpoints;
    private final OnEndpointChanged listener;

    public EndpointsModule(List<String> endpoints, OnEndpointChanged listener) {
        this.endpoints = endpoints;
        this.listener = listener;
    }

    @NonNull @Override public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.debug_drawer_module_endpoints, parent, false);
        Spinner spinner = (Spinner) view.findViewById(R.id.debug_network_endpoint);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(parent.getContext(), R.layout.debug_drawer_module_endpoints_spinner_item, endpoints);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (!endpoints.isEmpty()) {
            spinner.setSelection(0);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onEndpointChange(endpoints.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    @Override public void onOpened() {

    }

    @Override public void onClosed() {

    }

    @Override public void onStart() {

    }

    @Override public void onStop() {

    }
}
