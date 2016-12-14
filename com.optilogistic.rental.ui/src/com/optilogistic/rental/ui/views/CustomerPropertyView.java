package com.optilogistic.rental.ui.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;
import com.optilogistic.rental.core.RentalCoreActivator;

public class CustomerPropertyView extends ViewPart implements ISelectionListener{

	private Label customerLabel;
	
	public CustomerPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.horizontalSpacing = 1;
		parent.setLayout(gl_parent);
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText(Messages.RentalPropertyView_0);
		infoGroup.setLayout(new GridLayout(2, false));
		
		Label infoLabel = new Label(infoGroup, SWT.NONE);
		infoLabel.setText("Name: ");
		
		customerLabel = new Label(infoGroup, SWT.NONE);
		
		setCustomer(RentalCoreActivator.getAgency().getCustomers().get(0));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void setCustomer(Customer customer)
	{
		if(customer!=null){
		customerLabel.setText(customer.getDisplayName());
		}


	}
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(selection.isEmpty())
			return;
		if(selection instanceof IStructuredSelection)
		{
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			Customer c = Platform.getAdapterManager().getAdapter(selected, Customer.class);
			setCustomer(c);
		}
		
	}


}
