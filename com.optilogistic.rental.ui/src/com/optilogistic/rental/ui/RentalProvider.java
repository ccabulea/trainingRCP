package com.optilogistic.rental.ui;


import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider{

	@Override
	public Object[] getElements(Object inputElement) {
		Object[] result = null;
		if(inputElement instanceof Collection<?>)
		{
			result = ((Collection<?>) inputElement).toArray();
		}
		return result;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof RentalAgency)
		{
			return new Node[]{new Node(Node.CUSTOMERS, (RentalAgency)parentElement), new Node(Node.LOCATIONS, (RentalAgency)parentElement), new Node(Node.OBJECTS_A_LOUER, (RentalAgency)parentElement)};
		}
		if(parentElement instanceof Node)
		{
			return ((Node) parentElement).getChildren();
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		
		return (element instanceof RentalAgency) || (element instanceof Node);
	}

	@Override
	public String getText(Object element) {
		if(element instanceof RentalAgency)
		{
			return ((RentalAgency) element).getName();
		}
		if(element instanceof Node)
		{
			return ((Node)element).toString();
		}
		if(element instanceof Customer)
		{
			return ((Customer)element).getDisplayName();
		}
		if(element instanceof Rental)
		{
			return ((Rental)element).toString();
		}
		if(element instanceof RentalObject)
		{
			return ((RentalObject)element).getName();
		}
		return super.getText(element);
	}
	
	@Override
	public Color getForeground(Object element) {
		if(element instanceof Customer)
		{
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		}
		if(element instanceof RentalObject)
		{
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
		}
		if(element instanceof Rental)
		{
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if(element instanceof RentalAgency)
		{
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
		}
		if(element instanceof Node)
		{
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN);
		}
		return null;
	}
	
	public class Node{
		public static final String OBJECTS_A_LOUER = "Objects a louer";
		public static final String LOCATIONS = "Locations";
		public static final String CUSTOMERS = "Customers";
		String label;
		RentalAgency agency;
		
		public Node(String label, RentalAgency agency)
		{
			this.label = label;
			this.agency = agency;
			
		}
		Object[] getChildren(){
			if(this.label == CUSTOMERS)
			{
				return this.agency.getCustomers().toArray();
			}
			if(this.label == LOCATIONS)
			{
				return this.agency.getRentals().toArray();
			}
			if(this.label == OBJECTS_A_LOUER)
			{
				return this.agency.getObjectsToRent().toArray();
			}
			return new Object[0];
		}
		@Override
		public String toString() {
			
			return this.label;
		}
	}


}
