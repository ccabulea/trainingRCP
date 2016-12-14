package com.optilogistic.rental.ui.pref;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.optilogistic.rental.ui.RentalUIActivator;
import com.optilogistic.rental.ui.RentalUIConstants;

public class RentalColorPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, RentalUIConstants {

	public RentalColorPreferences() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
	}

	public RentalColorPreferences(int style) {
		super(style);
		// TODO Auto-generated constructor stub
	}

	public RentalColorPreferences(String title, int style) {
		super(title, style);
		// TODO Auto-generated constructor stub
	}

	public RentalColorPreferences(String title, ImageDescriptor image, int style) {
		super(title, image, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		addField( new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Customers:", getFieldEditorParent()));
		addField( new ColorFieldEditor(PREF_RENTAL_COLOR, "Rental:", getFieldEditorParent()));
		addField( new ColorFieldEditor(PREF_RENTAL_OBJECT_COLOR, "Objets:", getFieldEditorParent()));
	}

}
