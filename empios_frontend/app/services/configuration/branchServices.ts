import { createData, fetchData, updateData, deleteData } from "../apiService";

// 1. Create a new Branch (POST)
export const createBranch = async (Branch: any) => {
  return await createData("/api/branches", Branch);
};

// 2. Update an existing Branch (PUT)
export const updateBranch = async (id: number, Branch: any) => {
  return await updateData(`/api/branches/${id}`, Branch);
};

// 3. Get a Branch by name (GET)
export const getBranchByName = async (name: string) => {
  return await fetchData(`/api/branches/${name}`);
};

// 4. Get all Branches (GET)
export const getAllBranches = async () => {
  return await fetchData("/api/branches");
};

// 5. Search branches by keyword (GET)
export const searchBranches = async (keyword: string) => {
  return await fetchData(`/api/branches/search?keyword=${keyword}`);
};

// 6. Get all Branch names (GET)
export const getBranchNames = async () => {
  return await fetchData("/api/branches/names");
};

// 7. Delete a Branch by name (DELETE)
export const deleteBranch = async (name: string) => {
  return await deleteData(`/api/branches/${name}`);
};
