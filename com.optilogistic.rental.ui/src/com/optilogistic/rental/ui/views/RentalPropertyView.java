package com.optilogistic.rental.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.optilogistic.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart {

	private Label rentedObjectLabel;
	private Label customerLabel;
	
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan=2;
		gd.horizontalAlignment=SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		Label infoLabel = new Label(infoGroup, SWT.NONE);
		infoLabel.setText("Loue a: ");
		
		customerLabel = new Label(infoGroup, SWT.NONE);
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void setRental(Rental rental)
	{
		if(rental!=null){
		rentedObjectLabel.setText(rental.getRentedObject().getName());
		customerLabel.setText(rental.getCustomer().getDisplayName());
		}


	}

}
