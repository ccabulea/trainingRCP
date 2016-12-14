package com.optilogistic.rental.ui;


import java.util.Collection;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstants{

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
			//return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_CUSTOMER_COLOR));
		}
		if(element instanceof RentalObject)
		{
			//return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_COLOR));
		}
		if(element instanceof Rental)
		{
			//return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_OBJECT_COLOR));
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
	
	@Override
	public Image getImage(Object element) {
		if(element instanceof RentalAgency)
		{
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_AGENCY);
		}
		if(element instanceof Customer)
		{
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_CUSTOMER);
		}
		if(element instanceof Rental)
		{
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_RENTAL);
		}
		if(element instanceof RentalObject)
		{
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_RENTAL_OBJECT);
		}
		return super.getImage(element);
	}
	
	private Color getAColor(String rgbKey)
	{
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color col = colorRegistry.get(rgbKey);
		if(col==null)
		{
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col=colorRegistry.get(rgbKey);
		}
		return col;
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
		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}
	}


}
