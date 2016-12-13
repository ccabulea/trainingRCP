package com.optilogistic.rental.ui.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
	private Label startDateValLabel;
	private Label endDateValLabel;
	
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.horizontalSpacing = 1;
		parent.setLayout(gl_parent);
		
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
		new Label(parent, SWT.NONE);
		
		Group datesGroup = new Group(parent, SWT.NONE);
		datesGroup.setLayout(new GridLayout(2, false));
		GridData gd_datesGroup = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		datesGroup.setLayoutData(gd_datesGroup);
		datesGroup.setText("Dates de location");
		
		Label startDateLabel = new Label(datesGroup, SWT.NONE);
		startDateLabel.setText("du:");
		
		startDateValLabel = new Label(datesGroup, SWT.NONE);
		startDateValLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label endDateLabel = new Label(datesGroup, SWT.NONE);
		endDateLabel.setText("au:");
		
		endDateValLabel = new Label(datesGroup, SWT.NONE);
		endDateValLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		
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
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		startDateValLabel.setText(dateFormat.format(rental.getStartDate()));
		endDateValLabel.setText(dateFormat.format(rental.getEndDate()));
		}


	}
}
