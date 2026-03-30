import { test, expect } from '@playwright/test';

test.describe('EPAM Client Work navigation', () => {
  test('Navigate to Client Work from Services', async ({ page }) => {
    // Navigate to EPAM homepage
    await page.goto('https://www.epam.com', { waitUntil: 'networkidle' });

    // Dismiss cookie/privacy banner if present (best-effort)
    const acceptButtons = page.getByRole('button', { name: /accept|agree|ok/i });
    if (await acceptButtons.count()) {
      try {
        await acceptButtons.first().click({ timeout: 2000 });
      } catch (e) {
        // ignore if banner doesn't respond
      }
    }

    // Click the "Services" item in the header
    const servicesLink = page.getByRole('link', { name: /Services/i });
    await expect(servicesLink).toBeVisible({ timeout: 5000 });
    await servicesLink.click();

    // Click the "Explore Our Client Work" link
    // Using a case-insensitive regex to match variations in capitalization/text
    const exploreClientWork = page.getByRole('link', { name: /Explore( our)? client work/i });
    await expect(exploreClientWork).toBeVisible({ timeout: 5000 });
    await exploreClientWork.click();

    // Verify that the "Client Work" text is visible on the resulting page
    const clientWorkHeading = page.getByText(/Client Work/i);
    await expect(clientWorkHeading).toBeVisible({ timeout: 10000 });
  });
});
