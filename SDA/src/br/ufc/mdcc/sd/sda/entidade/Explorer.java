package br.ufc.mdcc.sd.sda.entidade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.tree.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

public class Explorer extends JFrame {
	private JTree fileTree;
	private FileSystemModel fileSystemModel;
	private JTextPane fileTP = new JTextPane();
	private JComboBox rootCombo = new JComboBox();

	public Explorer(String directory) {
		super("Explorer");
		JPanel treePanel = new JPanel();
		treePanel.setLayout(new BorderLayout());
		File[] roots = File.listRoots();
		for (int i = 0; i < roots.length; i++)
			rootCombo.addItem(roots[i].getPath());
		rootCombo.setSelectedItem("C:\\");
		rootCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileSystemModel = new FileSystemModel(new File(rootCombo
						.getSelectedItem().toString()));
				fileTree = new JTree(fileSystemModel);

			}
		});
		fileTP.setEditable(false);
		fileSystemModel = new FileSystemModel(new File(directory));
		fileTree = new JTree(fileSystemModel);
		fileTree.setEditable(true);
		fileTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent event) {
				File file = (File) fileTree.getLastSelectedPathComponent();
				setFileDetails(file);
			}
		});
		treePanel.add(rootCombo, BorderLayout.NORTH);
		treePanel.add(fileTree, BorderLayout.CENTER);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, new JScrollPane(treePanel), new JScrollPane(fileTP));
		getContentPane().add(splitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
	}

	// When a file/folder is clicked on the details are shown in the right pane
	private void setFileDetails(File file) {
		try {
			if (file == null)
				return;

			MutableAttributeSet attr = new SimpleAttributeSet();
			StyledDocument doc = fileTP.getStyledDocument();
			doc.remove(0, doc.getLength());
			StyleConstants.setForeground(attr, Color.blue);
			int offset = doc.getLength();
			doc.insertString(doc.getLength(), "Name: " + file.getName() + "\n",
					attr);
			doc.insertString(doc.getLength(), "Path: " + file.getPath() + "\n",
					attr);
			doc.insertString(doc.getLength(), "Size: " + file.length() + "\n",
					attr);

			if (file.isDirectory()) {
				File[] files = file.listFiles();
				Arrays.sort(files);
				doc.insertString(doc.getLength(), "\nContains " + files.length
						+ " Files/Folders:\n", attr);
				StyleConstants.setForeground(attr, Color.black);
				for (int i = 0; i < files.length; i++)
					doc.insertString(doc.getLength(),
							files[i].getName() + "\n", attr);
			}

			if (file.isFile()) {
				doc.insertString(
						doc.getLength(),
						"\nFile Contents:\n---------------------------------------------------\n",
						attr);
				try {
					StyleConstants.setForeground(attr, Color.black);
					DataInputStream inFile = new DataInputStream(
							new BufferedInputStream(new FileInputStream(
									file.getPath())));
					String line = "";
					while ((line = inFile.readLine()) != null)
						doc.insertString(doc.getLength(), line + "\n", attr);
					inFile.close();
				} catch (Exception e) {
					System.err
							.println("Error reading file:\n" + e.getMessage());
				}

			}
			fileTP.setCaretPosition(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Main Method
	public static void main(String args[]) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.err.println("Error setting the Look and Feel.");
		}
		new Explorer("C:\\");
	}
}

class FileSystemModel implements TreeModel {
	private File root;
	private Vector listeners = new Vector();

	public FileSystemModel(File rootDirectory) {
		root = rootDirectory;
	}

	public Object getRoot() {
		return root;
	}

	public Object getChild(Object parent, int index) {
		File directory = (File) parent;
		String[] children = directory.list();
		return new TreeFile(directory, children[index]);
	}

	public int getChildCount(Object parent) {
		File file = (File) parent;
		if (file.isDirectory()) {
			String[] fileList = file.list();
			if (fileList != null)
				return file.list().length;
		}
		return 0;
	}

	public boolean isLeaf(Object node) {
		File file = (File) node;
		return file.isFile();
	}

	public int getIndexOfChild(Object parent, Object child) {
		File directory = (File) parent;
		File file = (File) child;
		String[] children = directory.list();
		for (int i = 0; i < children.length; i++) {
			if (file.getName().equals(children[i])) {
				return i;
			}
		}
		return -1;

	}

	public void valueForPathChanged(TreePath path, Object value) {
		File oldFile = (File) path.getLastPathComponent();
		String fileParentPath = oldFile.getParent();
		String newFileName = (String) value;
		File targetFile = new File(fileParentPath, newFileName);
		oldFile.renameTo(targetFile);
		File parent = new File(fileParentPath);
		int[] changedChildrenIndices = { getIndexOfChild(parent, targetFile) };
		Object[] changedChildren = { targetFile };
		fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices,
				changedChildren);

	}

	private void fireTreeNodesChanged(TreePath parentPath, int[] indices,
			Object[] children) {
		TreeModelEvent event = new TreeModelEvent(this, parentPath, indices,
				children);
		Iterator iterator = listeners.iterator();
		TreeModelListener listener = null;
		while (iterator.hasNext()) {
			listener = (TreeModelListener) iterator.next();
			listener.treeNodesChanged(event);
		}
	}

	public void addTreeModelListener(TreeModelListener listener) {
		listeners.add(listener);
	}

	public void removeTreeModelListener(TreeModelListener listener) {
		listeners.remove(listener);
	}

	private class TreeFile extends File {
		public TreeFile(File parent, String child) {
			super(parent, child);
		}

		public String toString() {
			return getName();
		}
	}
}
