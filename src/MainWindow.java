package frogwhat;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class MainWindow {

	protected Shell shlFrogwhatVa;
	private Text inputBox;
	private Text outputBox;
	private Text targetsBox;
	private Table fillsList;
	private Text remainingTargets;
	private Table optiList;
	private Text slotBox;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlFrogwhatVa.open();
		shlFrogwhatVa.layout();
		while (!shlFrogwhatVa.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlFrogwhatVa = new Shell();
		shlFrogwhatVa.setSize(800, 600);
		shlFrogwhatVa.setText("frogwhat v0.9.1b");
		
		inputBox = new Text(shlFrogwhatVa, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		inputBox.setText("@RING:The Ring 'Demo' [+7] {Sh,Ca}\r\n@RING:The Ring 'Demo' {Ac,El,Co}\r\n@NECK:The Amulet 'Demo' {Cf,A:Recall}\r\n@LITE:The Feanorian Lamp 'Demo' (+4) {Po}\r\n@BODY:The Filthy Rag 'Demo' [1,+11] (+4) {Po,Ca}\r\n@BACK:The Cloak 'Demo' [1,+15] (+3) {Ac,A:Teleport}\r\n@HEAD:The Pointy Hat 'Demo' [1,+19] {Fi,Co,Rg}\r\n@ARMS:The Set of Gauntlets 'Demo' (+2,+2) [2,+10] {Co,Cf}\r\n@FEET:The Pair of Soft Leather Boots 'Demo' (+4,+3) [2,+11] {Ac,Nt,Di}\r\n@WEPN:The Broken Sword 'Demo' (4d2) (+8,+10) (+3) {Fi}\r\n@WEPN:The Main Gauche 'Demo' (1d6) (+18,+6) (+3) {Nx,Rg}\r\n@SHLD:The Mithril Shield 'Demo' [9,+14] (+3) {El}\r\n@SHOT:The Sling 'Demo' (x2.45) (+14,+12) [+8] (+1) {Sh}");
		inputBox.setBounds(10, 10, 764, 115);
		
		outputBox = new Text(shlFrogwhatVa, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		outputBox.setBounds(10, 444, 764, 107);
		
		fillsList = new Table(shlFrogwhatVa, SWT.BORDER | SWT.FULL_SELECTION);
		fillsList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Toggle the presence of the clicked piece of equipment in the outfit box
				TableItem ti = (TableItem) e.item;
				if (outputBox.getText().contains(ti.getText(1))) {
					//TODO: Figure out a better way to do this
					ArrayList<String> lines = new ArrayList<String>(Arrays.asList(outputBox.getText().split(System.lineSeparator())));
					lines.remove(ti.getText(1));
					outputBox.setText(String.join(System.lineSeparator(), lines));
				} else {
					outputBox.append(System.lineSeparator()+ti.getText(1));
					//TODO: figure out a way for this to not put a blank line at the top of the outfit box (or at least fix it with a kludge?)
				}
			}
		});
		fillsList.setHeaderVisible(true);
		fillsList.setBounds(10, 131, 251, 307);
		fillsList.setLinesVisible(true);
		
		TableColumn tblclmnSlots = new TableColumn(fillsList, SWT.NONE);
		tblclmnSlots.setWidth(40);
		tblclmnSlots.setText("Value");
		
		TableColumn tblclmnItemname = new TableColumn(fillsList, SWT.NONE);
		tblclmnItemname.setWidth(207);
		tblclmnItemname.setText("Item");
		
		optiList = new Table(shlFrogwhatVa, SWT.BORDER | SWT.FULL_SELECTION);
		optiList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Toggle the presence of the clicked piece of equipment in the outfit box
				TableItem ti = (TableItem) e.item;
				if (outputBox.getText().contains(ti.getText(1))) {
					//TODO: Figure out a better way to do this
					ArrayList<String> lines = new ArrayList<String>(Arrays.asList(outputBox.getText().split(System.lineSeparator())));
					lines.remove(ti.getText(1));
					outputBox.setText(String.join(System.lineSeparator(), lines));
				} else {
					outputBox.append(System.lineSeparator()+ti.getText(1));
					//TODO: figure out a way for this to not put a blank line at the top of the outfit box
				}
			}
		});
		optiList.setBounds(527, 131, 247, 307);
		optiList.setHeaderVisible(true);
		optiList.setLinesVisible(true);
		
		TableColumn tblclmnScore = new TableColumn(optiList, SWT.NONE);
		tblclmnScore.setWidth(43);
		tblclmnScore.setText("Score");
		
		TableColumn tblclmnItem = new TableColumn(optiList, SWT.NONE);
		tblclmnItem.setWidth(200);
		tblclmnItem.setText("Item");
		
		targetsBox = new Text(shlFrogwhatVa, SWT.BORDER | SWT.WRAP);
		targetsBox.setText("Ac,Ac,El,El,Fi,Fi,Co,Co,Po,Po,Li,Dk,Dk,Cf,Nt,Nx,So,Sh,Ca,Di,Bl,Hl,Hl,Rf");
		targetsBox.setBounds(267, 131, 254, 60);
		
		Button btnSearch = new Button(shlFrogwhatVa, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<String> targets = ItemHandle.splitQualities(targetsBox.getText()); //array of desired qualities, e.g. ["Ac", "Ac", "El", "Fi", "Co", "So", "Sh"]
				HashMap<String, Integer> slots = new HashMap<String, Integer>();
				for (String slot : ItemHandle.splitQualities(slotBox.getText())) {
					if (slots.containsKey(slot)) {
						slots.put(slot, 1+slots.get(slot));
					} else {
						slots.put(slot, 1);
					}
				}
				HashMap<String, ArrayList<String>> qualitySlots = new HashMap<String, ArrayList<String>>();
				Scanner outfitscan = new Scanner(outputBox.getText());
				while (outfitscan.hasNextLine()) {
					//Go through each item in the "outfit" list at the bottom of the screen. These are the equipped items, so their
					//qualities are removed from the list of desired qualities.
					String foolproofing = outfitscan.nextLine();
					if (!foolproofing.isEmpty()) {
						Equip tmpitem = new Equip(foolproofing);
						int slotcount = slots.get(tmpitem.getSlot());
						slots.put(tmpitem.getSlot(), slotcount-1); //So if boots are in the outfit list, FEET goes from 1 to 0.
						tmpitem.qualities.forEach((quality) -> targets.remove(quality)); //Remove outfit item stuff from the list of needed stuff
					}
				}
				remainingTargets.setText(String.join(",", targets));
				HashMap<Equip, Integer> fillmap = new HashMap<Equip, Integer>();
				HashMap<Equip,Integer> optimap = new HashMap<Equip,Integer>();
				Scanner housescan = new Scanner(inputBox.getText());
				while (housescan.hasNextLine()) {
					String foolproofing = housescan.nextLine();
					if (!foolproofing.isEmpty()) {
						Equip tmpitem = new Equip(foolproofing);
						if (slots.containsKey(tmpitem.getSlot()) && slots.get(tmpitem.getSlot()) > 0) {
							int tmpitemq = tmpitem.matchQualities(targets);
							if (tmpitemq > 0) {
								fillmap.put(tmpitem, tmpitemq);
								optimap.put(tmpitem, 0);
								for (String quality : tmpitem.qualities) {
									if (targets.contains(quality)) {
										String tmpslot = tmpitem.getSlot();
										if (!qualitySlots.containsKey(quality)) {
											qualitySlots.put(quality, new ArrayList<String>());
											qualitySlots.get(quality).add(tmpslot);
										} else if (!qualitySlots.get(quality).contains(tmpslot)) {
											qualitySlots.get(quality).add(tmpslot);
										}
									}
								}
							}
						}
					}
				}
				fillsList.removeAll();
				java.util.List<Entry<Equip,Integer>> sortedfillmap = ItemHandle.entriesSortedByValues(fillmap);
				for (Entry<Equip,Integer> listitem : sortedfillmap) {
					TableItem ti = new TableItem(fillsList, SWT.NONE);
					ti.setText(new String[] {listitem.getValue().toString(), listitem.getKey().itemText});
				}
				optiList.removeAll();
				HashMap<String,Integer> slotpercents = new HashMap<String,Integer>();
				qualitySlots.forEach((quality, slotlist) -> slotpercents.put(quality, (int) Math.round((1.0/slotlist.size())*100)));
				optimap.forEach((item, percent) -> {
					item.qualities.forEach((quality) -> {
						if (slotpercents.containsKey(quality) && slotpercents.get(quality) > percent) {
							optimap.put(item, slotpercents.get(quality));
						}
					});
				});
				java.util.List<Entry<Equip,Integer>> sortedoptimap = ItemHandle.entriesSortedByValues(optimap);
				for (Entry<Equip,Integer> listitem : sortedoptimap) {
					TableItem ti = new TableItem(optiList, SWT.NONE);
					ti.setText(new String[] {String.valueOf(listitem.getValue())+"%", listitem.getKey().itemText});
				}
			}
		});
		btnSearch.setBounds(267, 263, 254, 50);
		btnSearch.setText("Search");
		
		Button btnSaveList = new Button(shlFrogwhatVa, SWT.NONE);
		btnSaveList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shlFrogwhatVa, SWT.SAVE);
				fd.setFilterExtensions(new String[] {"*.txt"});
				if (fd.open() != null) {
					String filename = fd.getFilterPath() + System.getProperty("file.separator") + fd.getFileName();
					try {
						PrintWriter fileout = new PrintWriter(filename);
						fileout.print(targetsBox.getText() + System.lineSeparator() + slotBox.getText() + System.lineSeparator() + inputBox.getText());
						fileout.close();
					} catch (FileNotFoundException e1) {
						MessageBox iamerror = new MessageBox(shlFrogwhatVa, SWT.ICON_ERROR);
						iamerror.setText(e1.getClass().toString());
						iamerror.setMessage(e1.getMessage());
						iamerror.open();
					}
				}
			}
		});
		btnSaveList.setBounds(401, 319, 120, 25);
		btnSaveList.setText("Save Items");
		
		Button btnLoadList = new Button(shlFrogwhatVa, SWT.NONE);
		btnLoadList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shlFrogwhatVa, SWT.OPEN);
				fd.setFilterExtensions(new String[] {"*.txt"});
				if (fd.open() != null) {
					String filename = fd.getFilterPath() + System.getProperty("file.separator") + fd.getFileName();
					try {
						String[] tmp = Files.readString(Paths.get(filename)).split(System.lineSeparator(), 3);
						targetsBox.setText(tmp[0]);
						slotBox.setText(tmp[1]);
						inputBox.setText(tmp[2]);
					} catch (IOException e1) {
						MessageBox iamerror = new MessageBox(shlFrogwhatVa, SWT.ICON_ERROR);
						iamerror.setText(e1.getClass().toString());
						iamerror.setMessage(e1.getMessage());
						iamerror.open();
					} catch (ArrayIndexOutOfBoundsException e1) {
						MessageBox iamerror = new MessageBox(shlFrogwhatVa, SWT.ICON_ERROR);
						iamerror.setText(e1.getClass().toString());
						iamerror.setMessage("Items file has fewer than three lines of text.");
						iamerror.open();
					}
				}
			}
		});
		btnLoadList.setBounds(267, 319, 120, 25);
		btnLoadList.setText("Load Items");
		
		Button btnSaveOutfit = new Button(shlFrogwhatVa, SWT.NONE);
		btnSaveOutfit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shlFrogwhatVa, SWT.SAVE);
				fd.setFilterExtensions(new String[] {"*.txt"});
				if (fd.open() != null) {
					String filename = fd.getFilterPath() + System.getProperty("file.separator") + fd.getFileName();
					try {
						PrintWriter fileout = new PrintWriter(filename);
						fileout.print(outputBox.getText());
						fileout.close();
					} catch (FileNotFoundException e1) {
						MessageBox iamerror = new MessageBox(shlFrogwhatVa, SWT.ICON_ERROR);
						iamerror.setText(e1.getClass().toString());
						iamerror.setMessage(e1.getMessage());
						iamerror.open();
					}
				}
			}
		});
		btnSaveOutfit.setBounds(401, 350, 120, 25);
		btnSaveOutfit.setText("Save Outfit");
		
		Button btnLoadOutfit = new Button(shlFrogwhatVa, SWT.NONE);
		btnLoadOutfit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shlFrogwhatVa, SWT.OPEN);
				fd.setFilterExtensions(new String[] {"*.txt"});
				if (fd.open() != null) {
					String filename = fd.getFilterPath() + System.getProperty("file.separator") + fd.getFileName();
					try {
						outputBox.setText(Files.readString(Paths.get(filename)));
					} catch (IOException e1) {
						MessageBox iamerror = new MessageBox(shlFrogwhatVa, SWT.ICON_ERROR);
						iamerror.setText(e1.getClass().toString());
						iamerror.setMessage(e1.getMessage());
						iamerror.open();
					}
				}
			}
		});
		btnLoadOutfit.setBounds(267, 350, 120, 25);
		btnLoadOutfit.setText("Load Outfit");
		
		remainingTargets = new Text(shlFrogwhatVa, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP);
		remainingTargets.setText("Unmatched qualities (add outfit items and search again to clear these)");
		remainingTargets.setBounds(267, 381, 254, 57);
		
		slotBox = new Text(shlFrogwhatVa, SWT.BORDER | SWT.WRAP);
		slotBox.setText("BODY,BACK,HEAD,ARMS,FEET,RING,RING,NECK,LITE,SHOT,WEPN,SHLD");
		slotBox.setBounds(267, 197, 254, 60);
	}
}
