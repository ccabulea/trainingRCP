import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.opcoach.training.rental.Customer;
import com.optilogistic.rental.ui.RentalUIActivator;
import com.optilogistic.rental.ui.RentalUIConstants;

public class CopyHandler extends AbstractHandler implements RentalUIConstants{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Clipboard clipboard = new Clipboard(Display.getCurrent());
		String textData = "";
		
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer, ImageTransfer.getInstance()};
		
		
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if(currentSelection instanceof IStructuredSelection)
		{
			IStructuredSelection isel = (IStructuredSelection) currentSelection;
			for(Iterator<?> it = isel.iterator(); it.hasNext();)
			{
				Object selCustomer = it.next(); 
				if(selCustomer instanceof Customer)
				{
					textData=textData.concat(((Customer)selCustomer).getDisplayName()).concat(" ");
				}
			}
		}
		
		
		ImageData img = RentalUIActivator.getDefault().getImageRegistry().get(IMG_CUSTOMER).getImageData();		
		
		String rtfData = "{\\rtf1\\b\\i "+textData+"}";
		Object[] data = new Object[]{textData, rtfData, img};
		
		
		clipboard.setContents(data, transfers);
		clipboard.dispose();
		
		
		return null;
	}

}
