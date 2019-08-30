package guru.springframework.transform.note;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.NotesBean;
import guru.springframework.model.Notes;

public class NotesBeanTranformerTest {
	public static final Long ID_VALUE = 1L;
	public static final String RECIPE_NOTES = "LaTiDa";
	
	NotesBeanTransformer notesBeanTransformer;

	@Before
	public void setUp() throws Exception {
		notesBeanTransformer = new NotesBeanTransformer();
	}
	
	@Test
	public void testConvertNullNotesBean() {
		NotesBean notesBean = null;
		Notes notesEntity = notesBeanTransformer.convert(notesBean);
		assertNull(notesEntity);
	}
	
	@Test
	public void testConvertEmptyNotesBean() {
		NotesBean notesBean = new NotesBean();
		Notes notesEntity = notesBeanTransformer.convert(notesBean);
		assertNotNull(notesEntity);
	}

	@Test
	public void testConvertNotesBean() {
		NotesBean notesBean = new NotesBean();
		notesBean.setId(ID_VALUE);
		notesBean.setRecipeNotes(RECIPE_NOTES);
		
		Notes notesEntity = notesBeanTransformer.convert(notesBean);
		assertNotNull(notesEntity);
		assertEquals(ID_VALUE, notesEntity.getId());
		assertEquals(RECIPE_NOTES, notesEntity.getRecipeNotes());
	}

}
