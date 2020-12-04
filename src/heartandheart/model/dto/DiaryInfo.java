package heartandheart.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryInfo {
	private int diaryNo;
	private String userId;
	private String emotionNo;
	private String reportingDate;
	private String sleepingTime;
	private String diaryComment;
	private String isPublic;
	private String Code;
}
