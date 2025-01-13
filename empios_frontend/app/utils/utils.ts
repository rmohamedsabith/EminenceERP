const dateOnly = (data: string): string => {
  return data.split("T")[0]; // Split the string at "T" and get the first element
};

export default dateOnly;
