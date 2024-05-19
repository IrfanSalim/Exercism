class Badge {
    public String print(Integer id, String name, String department) {
      String idStr = id == null ? "" : "[" + id.toString() + "] - ";
      String departmentStr = department == null ? "OWNER" : department.toUpperCase();
      return String.format("%s%s - %s", idStr, name, departmentStr);
    }
}
