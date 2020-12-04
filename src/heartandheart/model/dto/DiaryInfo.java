package heartandheart.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryInfo {
	private int diaryNo;
	private String userId;
	private int emotionNo;
	private int weatherNo;
	private String reportingDate;
	private int sleepingTime;
	private String diaryComment;
	private int isPublic;
}
