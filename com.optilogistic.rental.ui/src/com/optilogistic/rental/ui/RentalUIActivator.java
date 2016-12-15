package com.optilogistic.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements RentalUIConstants{

	// The plug-in ID
	public static final String PLUGIN_ID = "com.optilogistic.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;
	
	private Map<String, Palette> palettes = new HashMap<>();
	
	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		getExtensionsQuickAccess();
		initPalettes();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}
	
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		
		Bundle b = FrameworkUtil.getBundle(this.getClass());
		
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
	}

	private void getExtensionsQuickAccess()
	{
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for(IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views"))
		{
			if(e.getName().equals("view"))
			{
				System.out.println("plugin: " + e.getNamespaceIdentifier()+" vue: "+e.getAttribute("name"));
			}
		}
	}
	
	private void initPalettes()
	{
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for(IConfigurationElement e : reg.getConfigurationElementsFor("com.optilogistic.rental.ui.Palette"))
		{
			try{
			Palette p = new Palette();
			p.setId(e.getAttribute("id"));
			p.setName(e.getAttribute("name"));
			
			p.setProvider((IColorProvider)e.createExecutableExtension("paletteClass"));
			
			palettes.put(p.getId(), p);
			
			System.out.println("The palette with the name "+p.getName()+" was added to the map");
			
			}catch(Exception exception)
			{}
		}
	}

	public Map<String, Palette> getPaletteManager() {
		return palettes;
	}
	
}
