"use client";
import Search from '@/app/components/Search';
import React, { ChangeEvent, FormEvent, useState } from 'react'

type FormData = {
  supplier: string;
  description: string;
  personName: string;
  personContact: string;
  companyName: string;
  address: string;
  fax: string;
  email: string;
  website: string;
  bankName: string;
  branch: string;
  accountNumber: string;
};

type FormErrors = Partial<Record<keyof FormData, boolean>>;

const page = () => {
  const [formData, setFormData] = useState<FormData>({
    supplier: "",
    description: "",
    personName: "",
    personContact: "",
    companyName: "",
    address: "",
    fax: "",
    email: "",
    website: "",
    bankName: "",
    branch: "",
    accountNumber: "",
  });

  const [errors, setErrors] = useState<FormErrors>({});

  const handleInputChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
    setErrors((prev) => ({ ...prev, [name]: !value }));
  };

  const validate = (): boolean => {
    const requiredFields: (keyof FormData)[] = [
      "supplier",
      "personName",
      "personContact",
      "companyName",
      "accountNumber",
      "bankName",
    ];

    const newErrors: FormErrors = {};
    requiredFields.forEach((field) => {
      if (!formData[field]) {
        newErrors[field] = true;
      }
    });

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (validate()) {
      console.log("Form data:", formData);
      // Send `formData` to backend via API
    }
  };

  const handleCancel = () => {
    setFormData({
      supplier: "",
      description: "",
      personName: "",
      personContact: "",
      companyName: "",
      address: "",
      fax: "",
      email: "",
      website: "",
      bankName: "",
      branch: "",
      accountNumber: "",
    });
    setErrors({});
  };
  return (
    <div>
      <form className=" border-b border-gray-900/10 pb-5" onSubmit={handleSubmit}>
        <div className="gap-5 mx-10 mt-5 grid grid-cols-3">
          <div className="border-r  border-gray-900/10 pr-5">
            <h2 className="text-base/7 text-center font-semibold text-gray-900">Supplier Info</h2>
            
            <div className="mt-3  gap-x-6  sm:grid-cols-6">
              <div className="sm:col-span-full pb-2">
                <label htmlFor="supplier" className="block text-sm/6 font-medium text-gray-900">
                  Supplier Name<span className="text-red-600">*</span>
                </label>
                <div className="mt-2">
                   <input
                      id="supplier"
                      name="supplier"
                      type="text"
                      value={formData.supplier}
                      onChange={handleInputChange}
                      className={`block w-full rounded-md px-3 py-1.5 text-base text-gray-900 outline outline-1 focus:outline focus:outline-2 focus:-outline-offset-2 ${
                        errors.supplier
                          ? "outline-red-600"
                          : "outline-gray-300 focus:outline-indigo-600"
                      }`}
                    />
                </div>
              </div>

              <div className="col-span-full pb-2">
                <label htmlFor="description" className="block text-sm/6 font-medium text-gray-900">
                  Description
                </label>
                <div className="mt-2">
                  <textarea
                    id="description"
                    name="description"
                    rows={3}
                    className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6"
                    value={formData.description}
                    onChange={handleInputChange}
                     
                  />
                </div>
                
              </div>

              <div className="sm:col-span-full pb-2">
                <label htmlFor="personName" className="block text-sm/6 font-medium text-gray-900">
                  Contact Person Name<span className="text-red-600">*</span>
                </label>
                <div className="mt-2">
                   <input
                      id="personName"
                      name="personName"
                      type="text"
                      value={formData.personName}
                      onChange={handleInputChange}
                      className={`block w-full rounded-md px-3 py-1.5 text-base text-gray-900 outline outline-1 focus:outline focus:outline-2 focus:-outline-offset-2 ${
                        errors.personName
                          ? "outline-red-600"
                          : "outline-gray-300 focus:outline-indigo-600"
                      }`}
                    />
        
                </div>
              </div>
              <div className="sm:col-span-full pb-2">
                <label htmlFor="personContact" className="block text-sm/6 font-medium text-gray-900">
                  Contact Number<span className="text-red-600">*</span>
                </label>
                <div className="mt-2">
                 <input
                    id="personContact"
                    name="personContact"
                    type="text"
                    value={formData.personContact}
                    onChange={handleInputChange}
                    className={`block w-full rounded-md px-3 py-1.5 text-base text-gray-900 outline outline-1 focus:outline focus:outline-2 focus:-outline-offset-2 ${
                      errors.personContact
                        ? "outline-red-600"
                        : "outline-gray-300 focus:outline-indigo-600"
                    }`}
                  />
                </div>
              </div>             
            </div>
          </div>

          <div className="border-r border-gray-900/10 pr-5">
            <h2 className="text-base/7 text-center font-semibold text-gray-900">Company Details</h2>
           
            <div className="mt-3  gap-x-6  sm:grid-cols-6">
              <div className="sm:col-span-full pb-2">
                <label htmlFor="companyName" className="block text-sm/6 font-medium text-gray-900">
                  Company Name<span className="text-red-600">*</span>
                </label>
                <div className="mt-2">
                  <input
                    id="companyName"
                    name="companyName"
                    type="text"
                    autoComplete="given-name"
                    value={formData.companyName}
                    onChange={handleInputChange}
                    className={`block w-full rounded-md px-3 py-1.5 text-base text-gray-900 outline outline-1 focus:outline focus:outline-2 focus:-outline-offset-2 ${
                      errors.companyName
                        ? "outline-red-600"
                        : "outline-gray-300 focus:outline-indigo-600"
                    }`}
                  />
                </div>
              </div>
              <div className="col-span-full pb-2">
                <label htmlFor="address" className="block text-sm/6 font-medium text-gray-900">
                  Address
                </label>
                <div className="mt-2">
                  <textarea
                    id="address"
                    name="address"
                    rows={3}
                    className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6"
                    defaultValue={''}
                  />
                </div>
                
              </div>

              <div className="sm:col-span-full pb-2">
                <label htmlFor="fax" className="block text-sm/6 font-medium text-gray-900">
                  Fax
                </label>
                <div className="mt-2">
                  <input
                    id="fax"
                    name="fax"
                    type="text"
                    autoComplete="family-name"
                    className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6"
                  />
                </div>
              </div>

              <div className="sm:col-span-full pb-2">
                <label htmlFor="email" className="block text-sm/6 font-medium text-gray-900">
                  Email address
                </label>
                <div className="mt-2">
                  <input
                    id="email"
                    name="email"
                    type="email"
                    autoComplete="email"
                    className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6"
                  />
                </div>
              </div>         

              <div className="col-span-full pb-2">
                <label htmlFor="website" className="block text-sm/6 font-medium text-gray-900">
                  Website
                </label>
                <div className="mt-2">
                  <input
                    id="website"
                    name="website"
                    type="text"
                    autoComplete="website"
                    className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6"
                  />
                </div>
              </div>
            </div>
          </div>

          <div>
            <h2 className="text-base/7 text-center font-semibold text-gray-900">Account Details</h2>
          
            <div className="mt-3  gap-x-6  sm:grid-cols-6">
              <div className="sm:col-span-full pb-2">
                <label htmlFor="bankName" className="block text-sm/6 font-medium text-gray-900">
                  Bank Name<span className="text-red-600">*</span>
                </label>
                <div className="mt-2">
                  <input
                    id="bankName"
                    name="bankName"
                    type="text"
                    autoComplete="given-name"
                    value={formData.bankName}
                    onChange={handleInputChange}
                    className={`block w-full rounded-md px-3 py-1.5 text-base text-gray-900 outline outline-1 focus:outline focus:outline-2 focus:-outline-offset-2 ${
                      errors.bankName
                        ? "outline-red-600"
                        : "outline-gray-300 focus:outline-indigo-600"
                    }`}
                  />
                </div>
              </div>
              <div className="col-span-full pb-2">
                <label htmlFor="branch" className="block text-sm/6 font-medium text-gray-900">
                  Branch
                </label>
                <div className="mt-2">
                <input
                    id="branch"
                    name="branch"
                    type="text"
                    autoComplete="given-name"
                    value={formData.branch}
                    onChange={handleInputChange}
                    className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6"
                  />
                </div>
                
              </div>

              <div className="sm:col-span-full pb-2">
                <label htmlFor="accountNumber" className="block text-sm/6 font-medium text-gray-900">
                  Account Number<span className="text-red-600">*</span>
                </label>
                <div className="mt-2">
                  <input
                    id="accountNumber"
                    name="accountNumber"
                    type="text"
                    autoComplete="family-name"
                    value={formData.accountNumber}
                    onChange={handleInputChange}
                    className={`block w-full rounded-md px-3 py-1.5 text-base text-gray-900 outline outline-1 focus:outline focus:outline-2 focus:-outline-offset-2 ${
                      errors.accountNumber
                        ? "outline-red-600"
                        : "outline-gray-300 focus:outline-indigo-600"
                    }`}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="mx-10  flex items-center justify-end gap-x-6">
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
      {/* Search and Table */}
      <div className="mx-10 mt-10">
        <Search options={[]} onSearch={()=>{}}/>

        <div className="relative flex flex-col w-full h-full overflow-y-auto text-gray-700 bg-white shadow-md rounded-lg bg-clip-border">
          <table className="w-full text-left table-auto min-w-max">
            <thead>
              <tr>
                <th className="p-4 border-b border-slate-300 bg-slate-50">
                  <p className="block text-sm font-normal leading-none text-slate-500">Supplier Name</p>
                </th>
                <th className="p-4 border-b border-slate-300 bg-slate-50">
                  <p className="block text-sm font-normal leading-none text-slate-500">Company Name</p>
                </th>
    
                <th className="p-4 border-b border-slate-300 bg-slate-50">
                  <p className="block text-sm font-normal leading-none text-slate-500">Contact Person Name</p>
                </th>
                <th className="p-4 border-b border-slate-300 bg-slate-50">
                  <p className="block text-sm font-normal leading-none text-slate-500">Contanct Number</p>
                </th>
                <th className="p-4 border-b border-slate-300 bg-slate-50"></th>
              </tr>
            </thead>
            <tbody>
              <tr className="hover:bg-slate-50 border-b border-slate-200">
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
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default page
