package com.nguoisaigon.util;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nguoisaigon.R;
import com.nguoisaigon.entity.ProductInfo;

@SuppressLint("SimpleDateFormat")
public class StoreProductDetailPageFragment extends Fragment {

	public static final String LIST_PRODUCT_INFO = "LIST_PRODUCT_INFO";

	public static final StoreProductDetailPageFragment newInstance(
			ProductInfo product) {
		StoreProductDetailPageFragment f = new StoreProductDetailPageFragment();
		Bundle bdl = new Bundle(1);
		String jsonListProduct = new Gson().toJson(product);
		bdl.putString(LIST_PRODUCT_INFO, jsonListProduct);
		f.setArguments(bdl);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.fragment_store_detail_page, container, false);

		String jsonProduct = getArguments().getString(LIST_PRODUCT_INFO);
		Log.i("StoreProductDetailPageFragment - onCreateView", "json product: "
				+ jsonProduct);

		ProductInfo product = new ProductInfo();
		product = new Gson().fromJson(jsonProduct,
				ProductInfo.class);
		TextView name = (TextView) rootView.findViewById(R.id.tvStoreDetailProductName);
		TextView description = (TextView) rootView.findViewById(R.id.tvStoreDetailProductDescription);
		ImageView hotTag = (ImageView) rootView.findViewById(R.id.storeDetailProductHotTag);
		ImageView newIcon = (ImageView) rootView.findViewById(R.id.storeDetailProductNewIcon);
		TextView sizeText = (TextView) rootView.findViewById(R.id.tvStoreDetailProductSizeText);
		ImageView sizeXXS = (ImageView) rootView.findViewById(R.id.storeDetailProductSizeXXS);
		ImageView sizeXS = (ImageView) rootView.findViewById(R.id.storeDetailProductSizeXS);
		ImageView sizeS = (ImageView) rootView.findViewById(R.id.storeDetailProductSizeS);
		ImageView sizeM = (ImageView) rootView.findViewById(R.id.storeDetailProductSizeM);
		ImageView sizeL = (ImageView) rootView.findViewById(R.id.storeDetailProductSizeL);
		ImageView sizeXL = (ImageView) rootView.findViewById(R.id.storeDetailProductSizeXL);
		TextView unitPrice = (TextView) rootView.findViewById(R.id.tvStoreDetailProductUnitPrice);
		TextView unitPriceText = (TextView) rootView.findViewById(R.id.tvStoreDetailProductUnitPriceText);
		ImageView salseIcon = (ImageView) rootView.findViewById(R.id.storeDetailProductSaleIcon);
		
		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/wg_legacy_edition.ttf");
		
		name.setTypeface(tf);
		description.setTypeface(tf);
		unitPrice.setTypeface(tf);
		unitPriceText.setTypeface(tf);
		sizeText.setTypeface(tf);
		
		
		name.setText(product.getName());
		description.setText(product.getDescription());
		unitPrice.setText(product.getUnitPrice() + " đ");
		
		sizeXXS.setImageAlpha(70);
		sizeXS.setImageAlpha(70);
		sizeS.setImageAlpha(70);
		sizeM.setImageAlpha(255);
		sizeL.setImageAlpha(70);
		sizeXL.setImageAlpha(70);
		
		sizeXXS.setContentDescription(product.getSizeQtyList().get(0).getSizeType().toString());
		sizeXS.setContentDescription(product.getSizeQtyList().get(1).getSizeType().toString());
		sizeS.setContentDescription(product.getSizeQtyList().get(2).getSizeType().toString());
		sizeM.setContentDescription(product.getSizeQtyList().get(3).getSizeType().toString());
		sizeL.setContentDescription(product.getSizeQtyList().get(4).getSizeType().toString());
		sizeXL.setContentDescription(product.getSizeQtyList().get(5).getSizeType().toString());
		
		if (product.getIsHot() < 1) {
			hotTag.setVisibility(ImageView.GONE);
		}
		
		if (product.getIsNew() < 1) {
			newIcon.setVisibility(ImageView.GONE);
		}
		
		if (product.getIsSale() < 1) {
			salseIcon.setVisibility(ImageView.GONE);
		}
		

		return rootView;
	}

}
