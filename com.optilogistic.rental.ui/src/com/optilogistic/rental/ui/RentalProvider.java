package com.optilogistic.rental.ui;


import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

public class RentalProvider extends LabelProvider implements ITreeContentProvider{

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
			return ((RentalAgency) parentElement).getCustomers().toArray();
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
		if(element instanceof RentalAgency)
		{
			if(((RentalAgency) element).getCustomers()!=null && !((RentalAgency) element).getCustomers().isEmpty()){
				return true;
			}
		}
		return false;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof RentalAgency)
		{
			return ((RentalAgency) element).getName();
		}
		if(element instanceof Customer)
		{
			return ((Customer)element).getDisplayName();
		}
		return super.getText(element);
	}
}
