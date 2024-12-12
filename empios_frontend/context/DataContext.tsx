import React, { createContext, useContext, ReactNode } from 'react'
import { useQuery } from '@tanstack/react-query'

// Define your data types (optional)
interface DataContextType {
  data: any
  isLoading: boolean
  error: any
}

// Create the context
const DataContext = createContext<DataContextType | undefined>(undefined)

// The provider component that will wrap the app
export const DataProvider = ({ children }: { children: ReactNode }) => {
  const { data, isLoading, error } = useQuery({
    queryKey: ['myData'],
    queryFn: async () => {
      const response = await fetch('/api/data')  // Example API request
      if (!response.ok) {
        throw new Error('Data fetch failed')
      }
      return response.json()
    },
  })

  return (
    <DataContext.Provider value={{ data, isLoading, error }}>
      {children}
    </DataContext.Provider>
  )
}

// Custom hook to use the data in any component
export const useData = () => {
  const context = useContext(DataContext)
  if (!context) {
    throw new Error('useData must be used within a DataProvider')
  }
  return context
}
