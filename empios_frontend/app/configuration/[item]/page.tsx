'use client';
import Search from '@/app/components/Search';
import { createCategory, getAllCategories } from '@/app/services/configuration/categoryServices';
import { useQuery } from '@tanstack/react-query';
import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';

// Utility function to format strings
const formatString = (str: string): string => {
  if (!str) return '';

  // Capitalize the first letter and insert spaces before uppercase letters
  return str.charAt(0).toUpperCase() + str.slice(1).replace(/([a-z])([A-Z])/g, '$1 $2');
};

interface PageProps {
  params: Promise<{ item?: string }>;
}

// Define the types for form data and errors
interface FormData {
  name: string;
  description: string;
  isMainBranch?: boolean;
}

interface FormErrors {
  name?: boolean;
}

const Page: React.FC<PageProps> = ({ params }) => {

  const [item, setItem] = useState<string | undefined>(undefined);
  const [formData, setFormData] = useState<FormData>({
    name: "",
    description: "",
    isMainBranch: false,
  });
    

  const [errors, setErrors] = useState<FormErrors>({});

  // const { data, error, isLoading } = useQuery(
  //   {
  //   queryKey: ['categories'],
  //   queryFn: getAllCategories,
  //   staleTime: 1000 * 60 * 5, // Cache for 5 minutes
  //   refetchOnWindowFocus: false, // Don't refetch on window focus
  //   retry: false, // Disable retrying the request
  // }
  // );

  // Unwrap the `params` promise using `React.use()`
  useEffect(() => {
    params.then(({ item }) => {
      setItem(item);
    });
  }, [params]);

  // Handle loading state
  if (!item) {
    return <p className="text-center mt-10 text-gray-500">Loading...</p>;
  }

  const isBranch=()=>{
    return item==="branch"
  }


  const handleInputChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value, type, checked } = e.target as HTMLInputElement;
    setFormData((prevData) => ({
      ...prevData,
      [name]: type === "checkbox" ? checked : value, // Update value based on checkbox type
    }));
    setErrors((prev) => ({ ...prev, [name]: !value }));
  };
  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (formData.name!=="") {
      switch(item)
      {
        case "category":createCategory(formData)
      }
    }else{
      setErrors({name:true});
    }
  };

  const handleCancel = () => {
    setFormData({
      name: "",
      description: "",
      isMainBranch:false,
    });
    setErrors({});
  };

  return (
    <div>
      <div className="mx-auto mt-16 max-w-xl sm:mt-8">
        <form action="#" method="POST" onSubmit={handleSubmit}>
          <div className="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
            <div className="sm:col-span-2">
              <label htmlFor="name" className="block text-sm/6 font-semibold text-gray-900">
                {formatString(item)} Name<span className="text-red-600 ">*</span>
              </label>
              <div className="mt-2.5">
                <input
                  id="name"
                  name="name"
                  type="text"
                  autoComplete="name"
                  value={formData.name}
                  onChange={handleInputChange}
                  className={`block w-full rounded-md px-3 py-1.5 text-base text-gray-900 outline outline-1 focus:outline focus:outline-2 focus:-outline-offset-2 ${
                    errors.name
                      ? "outline-red-600"
                      : "outline-gray-300 focus:outline-indigo-600"
                  }`}                />
              </div>
            </div>
            <div className="sm:col-span-2">
              <label htmlFor="description" className="block text-sm/6 font-semibold text-gray-900">
                Description
              </label>
              <div className="mt-2.5">
                <textarea
                  id="description"
                  name="description"
                  rows={3}
                  className="block w-full rounded-md bg-white px-3.5 py-2 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600"
                  value={formData.description}
                  onChange={handleInputChange}
                     
                />
              </div>
            </div>
            {isBranch()?<div className="flex gap-3">
              <div className="flex h-6 shrink-0 items-center">
                <div className="group grid size-4 grid-cols-1">
                  <input
                    id="candidates"
                    name="candidates"
                    type="checkbox"
                    aria-describedby="candidates-description"
                    className="col-start-1 row-start-1 appearance-none rounded border border-gray-300 bg-white checked:border-indigo-600 checked:bg-indigo-600 indeterminate:border-indigo-600 indeterminate:bg-indigo-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 disabled:border-gray-300 disabled:bg-gray-100 disabled:checked:bg-gray-100 forced-colors:appearance-auto"
                    checked={formData.isMainBranch}
                    onChange={handleInputChange}
                      
                  />
                  <svg
                    fill="none"
                    viewBox="0 0 14 14"
                    className="pointer-events-none col-start-1 row-start-1 size-3.5 self-center justify-self-center stroke-white group-has-[:disabled]:stroke-gray-950/25"
                  >
                    <path
                      d="M3 8L6 11L11 3.5"
                      strokeWidth={2}
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      className="opacity-0 group-has-[:checked]:opacity-100"
                    />
                    <path
                      d="M3 7H11"
                      strokeWidth={2}
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      className="opacity-0 group-has-[:indeterminate]:opacity-100"
                    />
                  </svg>
                </div>
              </div>
              <span className="block text-sm/6 font-semibold text-gray-900">Is Main Branch</span>
              
            </div>:null}
          </div>

          <div className="mx-10 mt-5 flex items-center justify-end gap-x-6">
          <button type="button" className="text-sm/6 font-semibold text-gray-900" onClick={handleCancel}>
            Cancel
          </button>
          <button
            type="submit"
            className="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
          >
            Save
          </button>
        </div>
        </form>
      </div>

      {/* Search and Table */}
      <div className="mx-10 mt-10">
      <Search options={[]} onSearch={()=>{}}/>

        <div className="relative flex flex-col w-full h-full overflow-y-auto text-gray-700 bg-white shadow-md rounded-lg bg-clip-border">
          <table className="w-full text-left table-auto min-w-max">
            <thead>
              <tr>
                <th className="p-4 border-b border-slate-300 bg-slate-50">
                  <p className="block text-sm font-normal leading-none text-slate-500">{formatString(item)} Name</p>
                </th>
                <th className="p-4 border-b border-slate-300 bg-slate-50">
                  <p className="block text-sm font-normal leading-none text-slate-500">Description</p>
                </th>
                {isBranch()?
                  <th className="p-4 border-b border-slate-300 bg-slate-50">
                  <p className="block text-sm font-normal leading-none text-slate-500">is Main Branch</p>
                  </th> :null
                }
                <th className="p-4 border-b border-slate-300 bg-slate-50">
                  <p className="block text-sm font-normal leading-none text-slate-500">Creator</p>
                </th>
                <th className="p-4 border-b border-slate-300 bg-slate-50">
                  <p className="block text-sm font-normal leading-none text-slate-500">Created Date</p>
                </th>
                <th className="p-4 border-b border-slate-300 bg-slate-50"></th>
              </tr>
            </thead>
            <tbody>
            {/* {data?.data?.map((item:any) => (
              <tr className="hover:bg-slate-50 border-b border-slate-200" key={item.id}>
                <td className="p-4 py-5">
                  <p className="block font-semibold text-sm text-slate-800">INV-1001</p>
                </td>
                <td className="p-4 py-5">
                  <p className="block text-sm text-slate-800">John Doe</p>
                </td>
                <td className="p-4 py-5">
                  <p className="block text-sm text-slate-800">$1,200.00</p>
                </td>
                <td className="p-4 py-5">
                  <p className="block text-sm text-slate-800">2024-08-01</p>
                </td>
                <td className="p-4 py-5">
                  <div className="text-center flex gap-10">
                    <button className="text-slate-600 hover:text-slate-800">
                      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
                        <path strokeLinecap="round" strokeLinejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                      </svg>
                    </button>
                    <button className="text-slate-600 hover:text-slate-800">
                      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
                        <path strokeLinecap="round" strokeLinejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                      </svg>
                    </button>
                  </div>
                </td>
              </tr>
            ))} */}

              
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Page;
