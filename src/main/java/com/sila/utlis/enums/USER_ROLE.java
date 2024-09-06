package com.sila.utlis.enums;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public enum USER_ROLE {
  ROLE_USER,
  ROLE_ADMIN
}
