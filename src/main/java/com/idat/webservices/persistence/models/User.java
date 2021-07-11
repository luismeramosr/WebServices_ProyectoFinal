package com.idat.webservices.persistence.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@Column(name = "id", length = 15)
	private int id;

	@Column(name = "firstName", length = 70)
	private String firstName;

	@Column(name = "lastName", length = 70)
	private String lastName;

	@Column(name = "dni", length = 8)
	private int dni;

	@Column(name = "phone", length = 9)
	private int phone;

	@Column(name = "email", length = 30)
	private String email;

	@Column(name = "username", length = 20)
	private String username;

	@Column(name = "password", length = 64)
	private String password;

	@Column(name = "active")
	private boolean active;

	@Column(name = "idRole", length = 15)
	private int idRole;

	@Column(name = "idSchedule")
	private int idSchedule;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idRole", insertable = false, updatable = false)
	private Role role;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idSchedule", insertable = false, updatable = false)
	private Schedule schedule;

	@JsonIgnore
	public boolean isInSchedule() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = LocalDateTime.of(LocalDate.now(), schedule.getStart());
		LocalDateTime stop = start.plusHours(
			Math.abs(
				schedule.getStart().getHour() -
				schedule.getStop().getHour()
			)
		).plusMinutes(
			Math.abs(
				schedule.getStart().getMinute() - 
				schedule.getStop().getMinute()
			)
		).plusSeconds(
			Math.abs(
				schedule.getStart().getSecond() -
				schedule.getStop().getSecond()
			)
		);
		return now.isAfter(start) && now.isBefore(stop);
	}

}
