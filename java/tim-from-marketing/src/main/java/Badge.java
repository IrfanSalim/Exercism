class Badge {
    public String print(Integer id, String name, String department) {
      String idStr = id == null ? "" : "[" + id.toString() + "] - ";
      String departmentStr = department == null ? "OWNER" : department.toUpperCase();
      return String.format("%s%s - %s", idStr, name, departmentStr);
    }
}


class Badge {
    public String print(Integer id, String name, String department) {
        return ((id == null) ? "" : "["+id+"] - ")+name+" - "+((department == null) ? "OWNER" : department.toUpperCase());
    }
}


class Badge {
    public String print(Integer id, String name, String department) {
        return String.format("%s%s - %s",
                id != null ? String.format("[%d] - ", id) : "",
                name,
                department != null ? department.toUpperCase() : "OWNER");
    }
}
