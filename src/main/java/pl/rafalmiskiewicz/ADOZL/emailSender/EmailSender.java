package pl.rafalmiskiewicz.ADOZL.emailSender;

public interface EmailSender {
	void sendEmail(String to, String subject, String content);
}
