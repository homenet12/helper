package damo.helper.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailDto {

	private String title;
	private String address;
	private String message;
}
