package guru.springframework.services;

import org.springframework.stereotype.Service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;

@Service
public class QuoteServiceImpl implements QuotesService {
	
	private final ChuckNorrisQuotes chuckNorrisQuotes;
	
	public QuoteServiceImpl() {
		this.chuckNorrisQuotes = new ChuckNorrisQuotes();
	}

	@Override
	public String getQuote() {
		//ChuckNorrisQuotes cnq = new ChuckNorrisQuotes();
		//return cnq.getRandomQuote();
		return chuckNorrisQuotes.getRandomQuote();
	}

}
