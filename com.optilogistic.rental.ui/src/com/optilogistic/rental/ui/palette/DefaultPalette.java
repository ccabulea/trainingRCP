package com.optilogistic.rental.ui.palette;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.optilogistic.rental.ui.RentalUIActivator;
import com.optilogistic.rental.ui.RentalUIConstants;
import com.optilogistic.rental.ui.RentalProvider.Node;

public class DefaultPalette implements IColorProvider, RentalUIConstants {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
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

}
