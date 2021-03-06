package seedu.resireg.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalStudents.ALICE;
import static seedu.resireg.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.resireg.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // different phone and email, same student ID -> returns true
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // different name, same student ID -> returns true
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // same name, same phone, different attributes (including student id) -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withFaculty(VALID_FACULTY_BOB).withStudentId(VALID_STUDENT_ID_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same email, different attributes (including student id) -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withFaculty(VALID_FACULTY_BOB).withStudentId(VALID_STUDENT_ID_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same phone, same email, different attributes (including student id) -> returns false
        editedAlice = new StudentBuilder(ALICE).withFaculty(VALID_FACULTY_BOB)
            .withStudentId(VALID_STUDENT_ID_BOB)
            .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSameStudent(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different faculty -> returns false
        editedAlice = new StudentBuilder(ALICE).withFaculty(VALID_FACULTY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different student ID -> returns false
        editedAlice = new StudentBuilder(ALICE).withStudentId(VALID_STUDENT_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
