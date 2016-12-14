package com.optilogistic.rental.ui.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.optilogistic.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart implements ISelectionListener{

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
		infoGroup.setText(Messages.RentalPropertyView_0);
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		
		GridData gd = new GridData();
		gd.horizontalSpan=2;
		gd.horizontalAlignment=SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		Label infoLabel = new Label(infoGroup, SWT.NONE);
		infoLabel.setText(Messages.RentalPropertyView_1);
		
		customerLabel = new Label(infoGroup, SWT.NONE);
		
		Group datesGroup = new Group(parent, SWT.NONE);
		datesGroup.setLayout(new GridLayout(2, false));
		GridData gd_datesGroup = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		datesGroup.setLayoutData(gd_datesGroup);
		datesGroup.setText(Messages.RentalPropertyView_2);
		
		Label startDateLabel = new Label(datesGroup, SWT.NONE);
		startDateLabel.setText(Messages.RentalPropertyView_3);
		
		startDateValLabel = new Label(datesGroup, SWT.NONE);
		startDateValLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label endDateLabel = new Label(datesGroup, SWT.NONE);
		endDateLabel.setText(Messages.RentalPropertyView_4);
		
		endDateValLabel = new Label(datesGroup, SWT.NONE);
		endDateValLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
		
		setLabelAsDragSource(rentedObjectLabel);
		setLabelAsDragSource(customerLabel);
		setLabelAsDragSource(startDateValLabel);
		setLabelAsDragSource(endDateValLabel);
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
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //$NON-NLS-1$
		startDateValLabel.setText(dateFormat.format(rental.getStartDate()));
		endDateValLabel.setText(dateFormat.format(rental.getEndDate()));
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
		if(selection instanceof IStructuredSelection)
		{
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			if(selected instanceof Rental)
			{
				setRental((Rental)selected);
			}
		}
		
	}
	
	private void setLabelAsDragSource(final Label label)
	{
		DragSource  source = new DragSource(label, DND.DROP_MOVE | DND.DROP_COPY);
		
		source.setTransfer(new Transfer[] {TextTransfer.getInstance()});
		
		source.addDragListener(new DragSourceAdapter(){
			public void dragSetData(DragSourceEvent event)
			{
				if(TextTransfer.getInstance().isSupportedType(event.dataType))
				{
					event.data = label.getText();
				}
			}
		});
	}
}
