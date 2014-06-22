package com.nguoisaigon.activity;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.nguoisaigon.R;
import com.nguoisaigon.util.WebService.WebServiceDelegate;

public class StoreMainActivity extends FragmentActivity implements
		WebServiceDelegate {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_main_layout);
	}

	public void menuStoreFashionManClick(View view) {

	}

	public void menuStoreFashionWomanClick(View view) {

	}

	public void menuStoreFashionKidClick(View view) {

	}

	public void menuStoreCosmeticManClick(View view) {

	}

	public void menuStoreCosmeticWomanClick(View view) {

	}

	public void menuStoreLifeStyleClick(View view) {

	}

	public void menuStoreFoodClick(View view) {

	}

	public void btnCloseClick(View view) {
		this.finish();
	}

	public void btnStoreCartClick(View view) {

	}
	
	public void btnStoreDetailBackClick(View view) {

	}
	
	public void btnStoreDetailEmailClick(View view) {

	}
	public void btnStoreDetailFacebookClick(View view) {

	}
	public void btnAddToCartClick(View view) {

	}

	@Override
	public void taskCompletionResult(JSONArray result) {
		// TODO Auto-generated method stub
		
	}
}
