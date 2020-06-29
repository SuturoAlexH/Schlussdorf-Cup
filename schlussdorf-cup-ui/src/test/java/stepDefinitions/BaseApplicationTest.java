package stepDefinitions;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Result;
import org.apache.commons.text.CaseUtils;
import org.testfx.framework.junit.ApplicationTest;
import resultDialog.ResultDialogAddTest;
import util.TestUtil;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public abstract class BaseApplicationTest extends ApplicationTest {

    public void selectImage(final String imageName) {
        File testImage = new File(ResultDialogAddTest.class.getResource("/images/" + imageName).getFile());
        TestUtil.setClipBoardContent(testImage.getAbsolutePath());

        Button imageButton = lookup("#selectImageButton").query();
        clickOn(imageButton)
                .press(KeyCode.CONTROL, KeyCode.V)
                .release(KeyCode.V, KeyCode.CONTROL)
                .push(KeyCode.ENTER);
    }

    public void selectRow(final int rowIndex){
        TableView<Result> resultTable = lookup("#table").query();

        await().atMost(60, TimeUnit.SECONDS).until(() -> Objects.nonNull(lookup(".table-row-cell")));
        TableRow<Result> row = lookup(".table-row-cell").nth(rowIndex-1).query();
        clickOn(row);
    }

    public void writeInputToTextField(final String input, final String textFieldName) {
        TextField textField = (TextField) findNodeByName(textFieldName);
        doubleClickOn(textField).clickOn(textField).write(input);
    }

    public void clickOnNode(final String name) {
        Node node = findNodeByName(name);
        clickOn(node);
    }

    public Node findNodeByName(final String name){
        String id = CaseUtils.toCamelCase(name, false);
        return lookup("#" + id).query();
    }

        public Stage getTopModalStage() {
        final List<Window> allWindows = listWindows();
        return (Stage) allWindows.get(1);
//        Collections.reverse(allWindows);
//
//        return (Stage) allWindows
//                .stream()
//                .filter(window -> window instanceof Stage)
//                .filter(window -> ((Stage) window).getModality() == Modality.APPLICATION_MODAL)
//                .findFirst()
//                .orElse(null);
    }
}
