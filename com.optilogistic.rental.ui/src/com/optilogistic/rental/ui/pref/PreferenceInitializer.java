package com.optilogistic.rental.ui.pref;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.optilogistic.rental.ui.RentalUIActivator;
import com.optilogistic.rental.ui.RentalUIConstants;

public class PreferenceInitializer extends AbstractPreferenceInitializer implements RentalUIConstants{

	private static final String ID_PALETTE_DEFAULT = "com.optilogistic.rental.ui.palettedefault";

	@Override
	public void initializeDefaultPreferences() {
		
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		store.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(10, 30, 100)));
		store.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN).getRGB()));
		store.setDefault(PREF_RENTAL_OBJECT_COLOR, StringConverter.asString(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA).getRGB()));
		
		store.setDefault(PREF_PALETTE, ID_PALETTE_DEFAULT);
	}
}
