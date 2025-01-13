import { createData, fetchData, updateData, deleteData } from "../apiService";

// 1. Create a new Brand (POST)
export const createBrand = async (Brand: any) => {
  return await createData("/api/brands", Brand);
};

// 2. Update an existing Brand (PUT)
export const updateBrand = async (id: number, Brand: any) => {
  return await updateData(`/api/brands/${id}`, Brand);
};

// 3. Get a Brand by name (GET)
export const getBrandByName = async (name: string) => {
  return await fetchData(`/api/brands/${name}`);
};

// 4. Get all Brands (GET)
export const getAllBrands = async () => {
  return await fetchData("/api/brands");
};

// 5. Search brands by keyword (GET)
export const searchBrands = async (keyword: string) => {
  return await fetchData(`/api/brands/search?keyword=${keyword}`);
};

// 6. Get all Brand names (GET)
export const getBrandNames = async () => {
  return await fetchData("/api/brands/names");
};

// 7. Delete a Brand by name (DELETE)
export const deleteBrand = async (name: string) => {
  return await deleteData(`/api/brands/${name}`);
};
