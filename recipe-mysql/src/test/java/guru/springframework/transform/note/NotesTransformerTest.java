package guru.springframework.transform.note;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.NotesBean;
import guru.springframework.model.Notes;

public class NotesTransformerTest {
	public static final Long ID_VALUE = 1L;
	public static final String RECIPE_NOTES = "LaTiDa";
	
	NotesTransformer notesTransformer;
	

	@Before
	public void setUp() throws Exception {
		notesTransformer = new NotesTransformer();
	}
	
	@Test
	public void testConvertNullNotesEntity(){
		Notes entityNotes = null;
		NotesBean notesBean = notesTransformer.convert(entityNotes);
		assertNull(notesBean);
	}
	
	@Test
	public void testConvertEmptyNotesEntity() {
		Notes entityNotes = new Notes();
		NotesBean notesBean = notesTransformer.convert(entityNotes);
		assertNotNull(notesBean);
	}

	@Test
	public void testConvertNotesEntity() {
		Notes entityNotes = new Notes();
		entityNotes.setId(ID_VALUE);
		entityNotes.setRecipeNotes(RECIPE_NOTES);
		NotesBean notesBean = notesTransformer.convert(entityNotes);
		assertNotNull(notesBean);
		assertEquals(ID_VALUE, notesBean.getId());
		assertEquals(RECIPE_NOTES, notesBean.getRecipeNotes());
		
	}

}
