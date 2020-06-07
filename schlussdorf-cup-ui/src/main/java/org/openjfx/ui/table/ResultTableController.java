package org.openjfx.ui.table;

import model.Result;
import com.javafxMvc.annotations.Bind;
import com.javafxMvc.annotations.Inject;
import com.javafxMvc.annotations.MVCController;
import com.javafxMvc.annotations.PostConstruct;
import org.openjfx.components.ErrorDialog;
import org.openjfx.constants.Folders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.LoadService;

import java.io.IOException;
import java.util.List;

@MVCController
public class ResultTableController implements ResultTableActions{

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultTableController.class);

    @Inject
    private ResultTableModel model;

    @Inject
    private ResultTableView view;

    private ErrorDialog loadErrorDialog = new ErrorDialog();

    private LoadService loadService = new LoadService();

    @Bind
    public void bindModelAndView() {
        view.table.itemsProperty().bindBidirectional(model.resultListProperty());
        model.selectedResultProperty().bind(view.table.getSelectionModel().selectedItemProperty());
    }

    @PostConstruct
    public void initialize() {
        try {
            List<Result> loadedResults = loadService.load(Folders.SAVE_FOLDER);
            model.getResultList().addAll(loadedResults);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            loadErrorDialog.show("Die Speicherdatei enthält einen Fehler und konnte nicht geladen werden.");
        }
    }
}