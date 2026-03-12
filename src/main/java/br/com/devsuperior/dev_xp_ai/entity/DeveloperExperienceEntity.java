package br.com.devsuperior.dev_xp_ai.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("tb_developer_experience")
public class DeveloperExperienceEntity {

    @Id
    private Long id;

    @Column("developer_id")
    private Long developerId;

    @Column("years_of_experience")
    private Integer yearsOfExperience;

    @Column("primary_language")
    private String primaryLanguage;

    @Column("interested_in_ai")
    private Boolean interestedInAi;

    @Column("skills")
    private String skills;

    public DeveloperExperienceEntity() {
    }

    public DeveloperExperienceEntity(Long id, Long developerId, Integer yearsOfExperience,
                                     String primaryLanguage, Boolean interestedInAi, String skills) {
        this.id = id;
        this.developerId = developerId;
        this.yearsOfExperience = yearsOfExperience;
        this.primaryLanguage = primaryLanguage;
        this.interestedInAi = interestedInAi;
        this.skills = skills;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDeveloperId() { return developerId; }
    public void setDeveloperId(Long developerId) { this.developerId = developerId; }

    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public String getPrimaryLanguage() { return primaryLanguage; }
    public void setPrimaryLanguage(String primaryLanguage) { this.primaryLanguage = primaryLanguage; }

    public Boolean getInterestedInAi() { return interestedInAi; }
    public void setInterestedInAi(Boolean interestedInAi) { this.interestedInAi = interestedInAi; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
}

