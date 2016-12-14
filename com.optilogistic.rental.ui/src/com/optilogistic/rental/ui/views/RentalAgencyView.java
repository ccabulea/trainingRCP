package com.optilogistic.rental.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.optilogistic.rental.core.RentalCoreActivator;
import com.optilogistic.rental.ui.RentalProvider;
import com.optilogistic.rental.ui.RentalUIActivator;

public class RentalAgencyView extends ViewPart implements IPropertyChangeListener{
	
	private TreeViewer tv;

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent);
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
	

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}
	
	@Override
	public void dispose() {
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		tv.refresh();
	}

}
