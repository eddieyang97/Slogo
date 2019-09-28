package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CodeInterpreter;

/**
 * MenuBar bar component of the UI; allows the user to change the colors of various
 * visualizer objects, change the image of the turtle, change language, choose file,
 * and see help file.
 *
 */
public class MenuBar extends Element implements SizingElement {
	
	public static final String BUTTON = "button";
	public static final String FILE = "OpenFile";
	public static final String BG = "BGColor";
	public static final String DISP = "Displays";
	public static final String TURTLE = "TurtleImage";
	public static final String LANGUAGE = "Language";
	public static final String HELP = "Help";
	public static final String WORKSPACE = "Workspace";
	public static final String ANIM = "Animate";
	public static final String PAUSE = "Pause";
	public static final String RESUME = "Resume";
	public static final String ON = "ON";
	public static final String OFF = "OFF";
	
	public static final String CN = "Chinese";
	public static final String ENG = "English";
	public static final String FRE = "French";
	public static final String GER = "German";
	public static final String ITA = "Italian";
	public static final String POR = "Portuguese";
	public static final String RUS = "Russian";
	public static final String SPA = "Spanish";
	
	protected static final String[] SUPPORTED_LANGS = {CN, ENG, FRE, SPA, GER};
	protected static final String[] ALL_LANGS = {CN, ENG, FRE, GER, ITA, POR, RUS, SPA};
	
	public static final String BASE_LANG = "resources/languages/";
	public static final String DEFAULT_LANG = BASE_LANG + ENG;
	
	public static final String GIF = ".gif";
	public static final String JPG = ".jpg";
	public static final String JPEG = "jpeg";
	public static final String PNG = ".png";
	public static final String TXT = ".txt";
	public static final String LOGO = "logo";
	
	public static final String FORMATALERT = "Incorrect File Format!";
	public static final String FORMATINFO = "The format of the file chosen is incorrect!";
	public static final String FILEALERT = "File Not Found!";
	public static final String LANGALERT = "Language not supported in UI!";
	public static final String LANGINFO = " is currently not supported by the UI, but the coding language has changed.";
	
	public static final int EXTENSIONLENGTH = 4;
	public static final int MENUY = 0;
	public static final int ALERTHEIGHT = 300;
	public static final double SLIDERMAX = 2;
	public static final double SLIDERVAL = 0.5;
	
	private TextBox tb;
	private ColorPicker bgColor;
	private ComboBox<String> langBox;
	private Menu dispBox;
	private List<ViewButton> buttons; 
	private int menuSpan;
	private final ResourceBundle defaultResource = ResourceBundle.getBundle(DEFAULT_LANG);
	
	/**
	 * Constructor for the menuBar
	 * @param stage
	 * @param vi
	 * @param tb
	 * @param ci
	 * @param d
	 */
	public MenuBar(Stage stage, ConcreteVisualizer vi, TextBox tb, CodeInterpreter ci, Display[] d) {
	    super(new GridPane());
	    ((GridPane) getPane()).setHgap(2);
	    this.tb = tb;
	    buttons = new ArrayList<>();
		menuSpan = 0;
		
		Set<String> supportedUILang = new HashSet<>(Arrays.asList(SUPPORTED_LANGS));
		FileChooser fc = new FileChooser();
		bgColor = new ColorPicker();
		langBox = new ComboBox<>();
		dispBox = new Menu();
        bgColor.setValue(ConcreteVisualizer.DEFAULTBGCOLOR);
        bgColor.getStyleClass().add(BUTTON);
        addElement(bgColor, eh -> vi.setBGColor(bgColor.getValue()));
        
        makeLang(ci, supportedUILang);
        makeDisp(d);
		makeWork();
		makeFile(stage, fc);
		makeHelp();
		makeAnimate(vi);
		makeSlider(vi);
		makePaused(vi);
		setLang(defaultResource);
	}
	
	private void makePaused(ConcreteVisualizer vi) {
		ViewButton pause = new ViewButton(PAUSE);
		addElement(pause, eh -> {
			vi.setPaused(!vi.getPaused());
			pause.setText((vi.getPaused()) ? RESUME : PAUSE);
		});
	}

	private void makeDisp(Display[] d) {
		for(Display disp : d) {
			CheckMenuItem mi = new CheckMenuItem(disp.getName());
			mi.setSelected(true);
			dispBox.getItems().add(mi);
			mi.setOnAction(e -> {
				disp.getActive().set(!disp.getActive().get());
			});
		}
		javafx.scene.control.MenuBar mb = new javafx.scene.control.MenuBar();
		mb.getMenus().add(dispBox);
		dispBox.setText(DISP);
		mb.getStyleClass().add(BUTTON);
		addElement(mb);
	}

	private void makeSlider(ConcreteVisualizer vi) {
		Slider sl = new Slider(0, SLIDERMAX, SLIDERVAL);
		addElement(sl);
		sl.setShowTickMarks(true);
		sl.setShowTickLabels(true);
		sl.setMajorTickUnit(SLIDERVAL);
		vi.setSliderListener(sl.valueProperty());
	}

	private void makeLang(CodeInterpreter ci, Set<String> supportedUILang) {
		langBox.getItems().addAll(ALL_LANGS);
		langBox.valueProperty().addListener(e -> changeLang(ci, supportedUILang));
		langBox.getStyleClass().add(BUTTON);

		addElement(langBox);
	}
	

	private void makeWork() {
		ViewButton workButton = new ViewButton(WORKSPACE);
		addElement(workButton, eh -> {
			Stage mystage = new Stage();
			mystage.setTitle(Main.TITLE);
			Scene scene = (new Gui()).setScene(mystage, Main.INIT_X_SIZE, Main.INIT_Y_SIZE);
			mystage.setScene(scene);
			mystage.show();
		});
	}

	private void makeFile(Stage stage, FileChooser fc) {
		ViewButton file = new ViewButton(FILE);
		addElement(file, eh -> {
			File openedFile = fc.showOpenDialog(stage);
			if(file != null) {
				openCodeFile(openedFile);
			}
		});
	}

	private void makeHelp() {
		ViewButton help = new ViewButton(HELP);
		addElement(help, eh -> new Help());
	}
	
	private void makeAnimate(ConcreteVisualizer vi) {
		ViewButton anim = new ViewButton(ANIM);
		addElement(anim, eh -> {
			vi.setAnimate(!vi.getAnimate());
			anim.setAdditionalText((vi.getAnimate()) ? ON : OFF);
		});
	}

	private void addElement(Object n, EventHandler<ActionEvent> eh) {
		Node node;
		if(n instanceof ViewButton) {
			((ViewButton) n).addHandler(eh);
			buttons.add((ViewButton) n);
			node = ((ViewButton) n).getButton();
		} else if(n instanceof ColorPicker) {
			((ColorPicker) n).setOnAction(eh);
			node = (ColorPicker) n;
		} else {
			node = (Node) n;
		}
		((GridPane) getPane()).add(node, menuSpan, MENUY);
		menuSpan++;
	}

	private void addElement(Object n) {
		addElement(n, eh -> {
			// Do nothing
		});
	}
	
	
	private void openCodeFile(File file) {
		if(file == null) {
			return;
		}
		try {
			String ext = file.getName().substring(file.getName().length() - EXTENSIONLENGTH);
			if(ext.equals(TXT) || ext.equals(LOGO)) {
		        Scanner sc = new Scanner(file);
	            StringBuilder s = new StringBuilder();
		        while(sc.hasNextLine()) {
		            s.append(sc.nextLine() + Console.NEWLINE);
		        }
		        tb.displayText(s.toString());
		        sc.close();
			} else {
				fileFormatAlert();
			}
		} catch(FileNotFoundException e) {
			System.out.println(FILEALERT);
		}
	}
	
	private void fileFormatAlert() {
		new MakeAlert(FORMATALERT, FORMATINFO);
	}

	private void noUILangAlert(String lang) {
		new MakeAlert(LANGALERT, lang+LANGINFO);
	}
	
	private void setLang(ResourceBundle myR) {
		bgColor.setPromptText(myR.getString(BG));
		langBox.setPromptText(myR.getString(LANGUAGE));
		tb.setLang(myR);
		buttons.forEach(a -> a.setLang(myR));
	}
	
	private void changeLang(CodeInterpreter ci, Set<String> supportedUILang) {
		String lang = langBox.getValue();
		try {
			ci.setProperties(lang);
		} catch (Exception e1) {
			new MakeAlert(FILEALERT, lang + LANGINFO);
		}
		if(supportedUILang.contains(lang)) {
			ResourceBundle newR = ResourceBundle.getBundle(BASE_LANG + lang);
			setLang(newR);
		} else {
			noUILangAlert(lang);
		}
	}

	/**
	 * sets the height of the menuBar
	 */
	public void setHeight(double h) {
		setPaneHeight(h);
	}

	/**
	 * sets the width of the menuBar
	 */
	public void setWidth(double w) {
		setPaneWidth(w);
	}

	/**
	 * sets the x position of the menbuBar
	 */
	@Override
	public void setElementX(double x) {
		this.setX(x);
	}

	/**
	 * sets the y position of the menbuBar
	 */
	@Override
	public void setElementY(double y) {
		this.setY(y);
	}
	
}