package de.dhbw.ase.timy.domain.templates;

import java.time.LocalDateTime;

public interface Timestamped {
    LocalDateTime getCreated();
    void setCreated(LocalDateTime created);
    LocalDateTime getUpdated();
    void setUpdated(LocalDateTime updated);
}
