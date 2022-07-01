package com.epam.songservice.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "songs")
@NoArgsConstructor
@AllArgsConstructor
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String artist;

  @Column
  private String album;

  @Column
  private String length;

  @Column
  private String year;

  @Column(name = "resource_id")
  private Long resourceId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Song song = (Song) o;

    if (id != null ? !id.equals(song.id) : song.id != null) {
      return false;
    }
    if (name != null ? !name.equals(song.name) : song.name != null) {
      return false;
    }
    if (artist != null ? !artist.equals(song.artist) : song.artist != null) {
      return false;
    }
    if (album != null ? !album.equals(song.album) : song.album != null) {
      return false;
    }
    if (year != null ? !year.equals(song.year) : song.year != null) {
      return false;
    }
    return resourceId != null ? resourceId.equals(song.resourceId) : song.resourceId == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (artist != null ? artist.hashCode() : 0);
    result = 31 * result + (album != null ? album.hashCode() : 0);
    result = 31 * result + (year != null ? year.hashCode() : 0);
    result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
    return result;
  }
}
