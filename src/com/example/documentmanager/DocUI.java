package com.example.documentmanager;

import java.io.File;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.data.util.TextFileProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class DocUI extends UI {

	FilesystemContainer docs = new FilesystemContainer(new File("/tmp/docs"));
	Table docList = new Table("Documents", docs);
	DocEditor docView = new DocEditor();
	
	protected void init(VaadinRequest request) {
		
		HorizontalSplitPanel split = new HorizontalSplitPanel();
		setContent(split);
		split.addComponent(docList);
		split.addComponent(docView);
		docList.setSizeFull();
		
		docList.addValueChangeListener(new ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				docView.setPropertyDataSource(new TextFileProperty((File) event.getProperty().getValue()));
			}
		});
		docList.setImmediate(true);
		docList.setSelectable(true);
	}

}