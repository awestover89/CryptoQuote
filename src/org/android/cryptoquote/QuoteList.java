package org.android.cryptoquote;

import java.util.LinkedList;
import java.util.Random;

import android.util.Log;

public class QuoteList{
	
	LinkedList<String> quotes;
	final String TAG = "MyActivity";
	
	public QuoteList(){
		quotes = new LinkedList<String>();
		//quotes.add("THIS IS A SAMPLE QUOTE -- ANTHONY WESTOVER");
		quotes.add(("The older one grows, the more one likes indecency." +
				" -- Virginia Woolf").toUpperCase());
		quotes.add(("Sometimes we could not bear the face of each other's " +
				"differences because of what we feared it might say about " +
				"ourselves. -- Audre Lorde").toUpperCase());
		quotes.add(("Everyone complains of his lack of memory, but nobody of " +
				"his want of judgment. -- La Rochefoucauld").toUpperCase());
		quotes.add(("I'm not dumb. I just have a command of thoroughly useless" +
				" information. -- Bill Watterson").toUpperCase());
		quotes.add(("Although prepared for martyrdom, I preferred that it be " +
				"postponed. -- Winston Churchill").toUpperCase());
		quotes.add(("The aim of art is to represent not the outward appearance of" +
				" things, but their inward significance. -- Aristotle").toUpperCase());
		quotes.add(("In summer, the song sings itself. -- " +
				"William Carlos Williams").toUpperCase());
		quotes.add(("Courage without conscience is a wild beast." +
				" -- Robert Ingersoll").toUpperCase());
		quotes.add(("The most boring thing in the entire world is" +
				" nudity. The second most boring thing is honesty." +
				" -- Chuck Palahniuk").toUpperCase());
		quotes.add(("The penalty for laughing in a courtroom is six " +
				"months in jail; if it were not for this penalty, the " +
				"jury would never hear the evidence. -- H.L. Mencken").toUpperCase());
		quotes.add(("If the only tool you have is a hammer, you tend to " +
				"see every problem as a nail. -- Abraham Maslow").toUpperCase());
		quotes.add(("Laws too gentle are seldom obeyed; to severe, seldom executed." +
				" -- Benjamin Franklin").toUpperCase());
		quotes.add(("Only the mediocre are always at their best. " +
				"-- Jean Giraudoux").toUpperCase());
		quotes.add(("An onion can make people cry, but there has never been a vegetable " +
				"invented to make them laugh. -- Will Rogers").toUpperCase());
		quotes.add(("The men the American public admire most extravagantly are the " +
				"most daring liars; the men they detest most violently are those " +
				"who try to tell them the truth. -- H.L. Mencken").toUpperCase());
		quotes.add(("The tragedy of old age is not that one is old, but that one" +
				" is young. -- Oscar Wilde").toUpperCase());
		quotes.add(("First say to yourself what you would be; and then do what" +
				" you have to do. -- Epictetus").toUpperCase());
		quotes.add(("There is no being of any race who, if he finds the proper " +
				"guide, cannot attain to virtue. -- Cicero").toUpperCase());
		quotes.add(("Generally speaking, the Way of the warrior is the resolute " +
				"acceptance of death. -- Miyamoto Musashi").toUpperCase());
		quotes.add(("Humor is a drug which it's the fashion to abuse." +
				" -- W.S. Gilbert").toUpperCase());
		quotes.add(("People are difficult to govern because they have " +
				"too much knowledge. -- Lao-tzu").toUpperCase());
		quotes.add(("People who count their chickens before they are hatched, " +
				"act very wisely, because chickens run about so absurdly that " +
				"it is impossible to count them accurately. " +
				"-- Oscar Wilde").toUpperCase());
		quotes.add(("I've been trying for some time to develop a lifestyle " +
				"that doesn't require my presence. -- Garry Trudeau").toUpperCase());
		quotes.add(("It is much harder to judge yourself than to judge others." +
				" If you succeed in judging yourself, it's because you're truly " +
				"a wise man. -- Antoine de Saint-Exupery").toUpperCase());
		quotes.add(("Absence diminishes small loves and increases great ones, " +
				"as the wind blows out the candle and fans the bonfire. " +
				"-- La Rochefoucald").toUpperCase());
		quotes.add(("Heroism on command, senseless violence, and all the " +
				"loathsome nonsense that goes by the name of patriotism - " +
				"how passionately I hate them! -- Albert Einstein").toUpperCase());
		quotes.add(("The less you can live on, the more chance your idea will " +
				"succeed. This is true even after you’ve 'made it'. " +
				"-- Hugh Macleod").toUpperCase());
		quotes.add(("Who makes quick use of the moment is a genius of " +
				"prudence. -- Johann Kaspar Lavater").toUpperCase());
		quotes.add(("When two friends part they should lock up each other's " +
				"secrets and exchange keys. The truly noble mind has no " +
				"resentments. -- Diogenes").toUpperCase());
		quotes.add(("The here and now is all we have, and if we play it " +
				"right it's all we'll need. -- Ann Richards").toUpperCase());
		quotes.add(("We must learn to be still in the midst of activity and " +
				"to be vibrantly alive in repose. -- Indira Gandhi").toUpperCase());
		quotes.add(("Let the fear of danger be a spur to prevent it; he that fears " +
				"not, gives advantage to the danger. -- Francis Quarles").toUpperCase());
		quotes.add(("A fellow who is always declaring he's no fool usually" +
				" has his suspicions. -- Wilson Mizner").toUpperCase());
		quotes.add(("It is not fair to ask of others what you are unwilling" +
				" to do yourself. -- Eleanor Roosevelt").toUpperCase());
		quotes.add(("It is impossible to go through life without trust: That " +
				"is to be imprisoned in the worst cell of all, oneself. " +
				"-- Graham Greene").toUpperCase());
		quotes.add(("Where all think alike, no one thinks very much." +
				" -- Walter Lippmann").toUpperCase());
		quotes.add(("Much learning does not teach understanding. " +
				"-- Heraclitus").toUpperCase());
	}
	
	public String getQuote(){
		Random rng = new Random();
		Log.v(TAG,quotes.size()+"");
		int r = rng.nextInt(quotes.size()-1);
		return quotes.get(r-1);
	}

}
