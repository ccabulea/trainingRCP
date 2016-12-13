package com.optilogistic.rental.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.optilogistic.rental.core.RentalCoreActivator;
import com.optilogistic.rental.ui.RentalProvider;

public class RentalAgencyView extends ViewPart {

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		TreeViewer tv = new TreeViewer(parent);
		RentalProvider rp = new RentalProvider();
		tv.setContentProvider(rp);
		tv.setLabelProvider(rp);
		List<RentalAgency> collection = new ArrayList<>();
		collection.add(RentalCoreActivator.getAgency());
		tv.setInput(collection);
		
		getSite().setSelectionProvider(tv);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
