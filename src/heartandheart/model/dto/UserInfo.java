package heartandheart.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	private String id;
	private int pw;
	private String matchingID;	
}
