package ru.zencoding.eclipse.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import ru.zencoding.JSExecutor;
import ru.zencoding.eclipse.EclipseZenEditor;
import ru.zencoding.eclipse.TabKeyHandler;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ExpandAbbreviationAction extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public ExpandAbbreviationAction() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		expand();
		return null;
	}
	
	public static boolean expand() {
		ActionRunner runner = ActionRunner.getSingleton();
		EclipseZenEditor editor = runner.getEditor();
		JSExecutor js = JSExecutor.getSingleton();
		String profileName = "eclipse";
		
		if (editor != null) {
			try {
				// force tab key handler installation
				TabKeyHandler.install(editor.getEditor());
				
				// expand abbreviation with current profile
				return js.runAction(editor, "expand_abbreviation", editor.getSyntax(), profileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return false;
	}
}
