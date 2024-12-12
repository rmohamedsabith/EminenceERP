// ApiResponse Interface
export interface ApiResponse<T> {
  data: T;
  statusCode: number;
  message: string;
}

// Generic function for API calls
const apiCall = async <T>(
  url: string,
  method: string,
  body?: any
): Promise<ApiResponse<T>> => {
  const response = await fetch(url, {
    method,
    headers: {
      "Content-Type": "application/json",
    },
    body: body ? JSON.stringify(body) : undefined,
  });

  // Check if the response is not ok
  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message || "An error occurred");
  }

  // Parse the response data
  const data = await response.json();

  // Construct the ApiResponse with data, status, and message
  return {
    data: data.data,
    statusCode: data.statusCode,
    message: data.message || "Request was successful",
  };
};

// 1. Create - POST
export const createData = async (
  url: string,
  payload: any
): Promise<ApiResponse<any>> => {
  return apiCall<any>(url, "POST", payload);
};

// 2. Read - GET
export const fetchData = async (url: string): Promise<ApiResponse<any>> => {
  return apiCall<any>(url, "GET");
};

// 3. Update - PUT
export const updateData = async (
  url: string,
  payload: any
): Promise<ApiResponse<any>> => {
  return apiCall<any>(url, "PUT", payload);
};

// 4. Patch - PATCH
export const patchData = async (
  url: string,
  payload: any
): Promise<ApiResponse<any>> => {
  return apiCall<any>(url, "PATCH", payload);
};

// 5. Delete - DELETE
export const deleteData = async (url: string): Promise<ApiResponse<any>> => {
  return apiCall<any>(url, "DELETE");
};
